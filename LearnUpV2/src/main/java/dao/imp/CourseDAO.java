package dao.imp;

import dao.IModelDAO;
import config.MongoClientProvider;
import exception.DaoException;
import exception.EntityNotFoundException;
import model.Course;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CourseDAO implements IModelDAO<Course> {

    private static final String COLLECTION_NAME = "courses";
    private final MongoCollection<Course> collection; 

    public CourseDAO() {
        this.collection = MongoClientProvider.getInstance().getCollection(COLLECTION_NAME, Course.class);
    }

    @Override
    public Course create(Course entity) throws DaoException {
        if (entity.get_id() == null) {
            entity.set_id(new ObjectId()); 
        }
        Date now = new Date();
        entity.setCreatedAt(now); 
        entity.setUpdatedAt(now);
        
        try {
            collection.insertOne(entity);
            return entity;
        } catch (Exception e) {
            throw new DaoException("Error al crear el curso en MongoDB: " + e.getMessage(), e);
        }
    }

    @Override
    public Course findById(ObjectId id) throws EntityNotFoundException, DaoException {
        try {
            Course course = collection.find(Filters.eq("_id", id)).first();
            if (course == null) {
                throw new EntityNotFoundException(id, Course.class);
            }
            return course;
        } catch (Exception e) {
            throw new DaoException("Error al buscar curso por ID: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Course> findAll() throws DaoException {
        List<Course> courses = new ArrayList<>();
        try {
            collection.find().into(courses);
            return courses;
        } catch (Exception e) {
            throw new DaoException("Error al buscar todos los cursos: " + e.getMessage(), e);
        }
    }

    @Override
    public Course update(ObjectId id, Course entity) throws EntityNotFoundException, DaoException {
        entity.setUpdatedAt(new Date()); 
        Bson filter = Filters.eq("_id", id);
        
        Bson updates = Updates.combine(
            Updates.set("title", entity.getTitle()),
            Updates.set("description", entity.getDescription()),
            Updates.set("category", entity.getCategory()),
            Updates.set("instructorId", entity.getInstructorId()),
            Updates.set("price", entity.getPrice()),
            Updates.set("rating", entity.getRating()),
            Updates.set("tags", entity.getTags()),
            Updates.set("modules", entity.getModules()),
            Updates.set("updatedAt", entity.getUpdatedAt())
        );

        try {
            UpdateResult result = collection.updateOne(filter, updates);
            if (result.getModifiedCount() == 0) {
                throw new EntityNotFoundException(id, Course.class);
            }
            return findById(id);
        } catch (EntityNotFoundException e) {
             throw e;
        } catch (Exception e) {
            throw new DaoException("Error al actualizar el curso: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteById(ObjectId id) throws EntityNotFoundException, DaoException {
        try {
            DeleteResult result = collection.deleteOne(Filters.eq("_id", id));
            if (result.getDeletedCount() == 0) {
                throw new EntityNotFoundException(id, Course.class);
            }
        } catch (Exception e) {
             throw new DaoException("Error al eliminar curso por ID: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteAll() throws DaoException {
        try {
            collection.deleteMany(new org.bson.Document());
        } catch (Exception e) {
            throw new DaoException("Error al eliminar todos los cursos: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Course> findByNombre(String title) throws DaoException {
        List<Course> courses = new ArrayList<>();
        try {
            Bson filter = Filters.regex("title", "^" + title, "i"); 
            collection.find(filter).into(courses);
            return courses;
        } catch (Exception e) {
            throw new DaoException("Error al buscar curso por título: " + e.getMessage(), e);
        }
    }
}