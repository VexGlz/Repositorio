package dao;

import dao.exceptions.DaoException;
import dao.exceptions.EntityNotFoundException;
import model.Producto;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface IProductoDAO {

    Producto create(Producto producto) throws DaoException;

    Optional<Producto> findById(ObjectId id) throws DaoException;

    List<Producto> findAll() throws DaoException;

    boolean update(Producto producto) throws DaoException, EntityNotFoundException;

    boolean deleteById(ObjectId id) throws DaoException, EntityNotFoundException;

    long deleteAll() throws DaoException;

    List<Producto> findByNombre(String nombre) throws DaoException;

    List<Producto> findByCategoria(String categoria) throws DaoException;
}