package exception;

import org.bson.types.ObjectId;

public class EntityNotFoundException extends DaoException {
    public EntityNotFoundException(ObjectId id, Class<?> entityClass) {
        super("Entidad " + entityClass.getSimpleName() + " con ID " + id + " no encontrada.");
    }
    public EntityNotFoundException(String message) {
        super(message);
    }
}
