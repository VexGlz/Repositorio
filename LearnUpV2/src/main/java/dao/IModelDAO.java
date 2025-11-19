package dao;

import exception.DaoException;
import exception.EntityNotFoundException;
import java.util.List;
import org.bson.types.ObjectId;

public interface IModelDAO<T> {
    T create(T entity) throws DaoException;
    T findById(ObjectId id) throws EntityNotFoundException, DaoException;
    List<T> findAll() throws DaoException;
    T update(ObjectId id, T entity) throws EntityNotFoundException, DaoException;
    void deleteById(ObjectId id) throws EntityNotFoundException, DaoException;
    void deleteAll() throws DaoException;
    List<T> findByNombre(String name) throws DaoException;
}