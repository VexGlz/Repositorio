package controllers.imp;

import config.MongoClientProvider;
import dao.IModelDAO;
import dao.imp.CourseDAO;
import exception.DaoException;
import exception.EntityNotFoundException;
import model.Course;
import model.Module;
import model.Resource;
import org.bson.types.ObjectId;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MainController {

    public static void main(String[] args) {
        IModelDAO<Course> courseDAO = new CourseDAO();
        
        try {
            Course newCourse = createSampleCourse();
            Course createdCourse = courseDAO.create(newCourse);
            System.out.println("Curso creado: " + createdCourse.getTitle() + " con ID: " + createdCourse.get_id());

            ObjectId courseId = createdCourse.get_id();
            Course foundCourse = courseDAO.findById(courseId);
            System.out.println("Curso encontrado por ID: " + foundCourse.getTitle());

            foundCourse.setPrice(99.99); 
            Course updatedCourse = courseDAO.update(courseId, foundCourse);
            System.out.println("Curso actualizado. Nuevo precio: " + updatedCourse.getPrice());

            List<Course> searchResults = courseDAO.findByNombre("Introducción");
            System.out.println("Cursos encontrados por nombre: " + searchResults.size());
            
        } catch (EntityNotFoundException e) {
            System.err.println("ERROR: Entidad no encontrada: " + e.getMessage());
        } catch (DaoException e) {
            System.err.println("ERROR en la operación DAO: " + e.getMessage());
        } finally {
            MongoClientProvider.getInstance().close();
            System.out.println("Cerrada la conexión a MongoDB.");
        }
    }
    
    private static Course createSampleCourse() {
        Resource video = new Resource("video", "Clase 1: Configuración", "http://example.com/vid1");
        Module module1 = new Module("Módulo Inicial", 60, Arrays.asList(video));
        
        return new Course(
            "Introducción a MongoDB con Java",
            "Aprende a usar el driver de Mongo y la persistencia de datos.",
            "Base de Datos",
            new ObjectId(), 
            0.0,
            4.8,
            Arrays.asList("mongodb", "java", "nosql"),
            Arrays.asList(module1)
        );
    }
}