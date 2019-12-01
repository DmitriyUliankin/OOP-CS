package Data.Repository;

import Data.Entities.IEntity;
import Exceptions.Entities.EntityAlreadyExistException;
import Exceptions.Entities.EntityNotFoundException;

import java.util.List;

public interface IEntityRepository<TKey, T extends IEntity<TKey>> {
    T Get(TKey key) throws EntityNotFoundException;
    List<T> List();
    void Create(T entity) throws EntityAlreadyExistException;
    void Update(T entity) throws EntityNotFoundException;
}
