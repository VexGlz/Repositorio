package dao.mongo;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import config.MongoClientProvider;
import dao.IProductoDAO;
import dao.exceptions.DaoException;
import dao.exceptions.EntityNotFoundException;
import model.Producto;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductoDAO implements IProductoDAO {

    private final MongoCollection<Producto> collection;

    public ProductoDAO() {
        this.collection = MongoClientProvider.getInstance()
                .getCollection("productos", Producto.class);
    }

    @Override
    public Producto create(Producto producto) throws DaoException {
        try {
            producto.set_id(new ObjectId());
            producto.setCreatedAt(LocalDateTime.now());
            producto.setUpdatedAt(LocalDateTime.now());
            
            collection.insertOne(producto);
            return producto; 
        } catch (MongoException e) {
            throw new DaoException("Error al crear el producto", e);
        }
    }

    @Override
    public Optional<Producto> findById(ObjectId id) throws DaoException {
        try {
            Producto producto = collection.find(Filters.eq("_id", id)).first();
            return Optional.ofNullable(producto);
        } catch (MongoException e) {
            throw new DaoException("Error al buscar el producto por ID", e);
        }
    }

    @Override
    public List<Producto> findAll() throws DaoException {
        try {
            return collection.find().into(new ArrayList<>());
        } catch (MongoException e) {
            throw new DaoException("Error al listar todos los productos", e);
        }
    }

    @Override
    public boolean update(Producto producto) throws DaoException, EntityNotFoundException {
        try {
            if (producto.get_id() == null) {
                throw new IllegalArgumentException("El producto debe tener un ID para ser actualizado.");
            }
            producto.setUpdatedAt(LocalDateTime.now());
            
            UpdateResult result = collection.replaceOne(
                    Filters.eq("_id", producto.get_id()), 
                    producto
            );
            
            if (result.getMatchedCount() == 0) {
                throw new EntityNotFoundException("Producto no encontrado con ID: " + producto.get_id());
            }
            
            return result.getModifiedCount() > 0;
        } catch (MongoException e) {
            throw new DaoException("Error al actualizar el producto", e);
        }
    }

    @Override
    public boolean deleteById(ObjectId id) throws DaoException, EntityNotFoundException {
        try {
            DeleteResult result = collection.deleteOne(Filters.eq("_id", id));
            
            if (result.getDeletedCount() == 0) {
                 throw new EntityNotFoundException("Producto no encontrado con ID: " + id);
            }
            
            return result.getDeletedCount() > 0;
        } catch (MongoException e) {
            throw new DaoException("Error al eliminar el producto", e);
        }
    }

    @Override
    public long deleteAll() throws DaoException {
         try {
            DeleteResult result = collection.deleteMany(Filters.empty());
            return result.getDeletedCount();
        } catch (MongoException e) {
            throw new DaoException("Error al eliminar todos los productos", e);
        }
    }

    @Override
    public List<Producto> findByNombre(String nombre) throws DaoException {
        try {
            return collection.find(Filters.eq("nombre", nombre)).into(new ArrayList<>());
        } catch (MongoException e) {
            throw new DaoException("Error al buscar por nombre", e);
        }
    }

    @Override
    public List<Producto> findByCategoria(String categoria) throws DaoException {
         try {
            return collection.find(Filters.in("categorias", categoria)).into(new ArrayList<>());
        } catch (MongoException e) {
            throw new DaoException("Error al buscar por categoría", e);
        }
    }
}