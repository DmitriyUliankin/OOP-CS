package Data.Repository;

import Exceptions.Entities.EntityNotFoundException;

import java.util.List;

public interface IEntityRepository<T> {
    T Get(int id) throws EntityNotFoundException;
    List<T> List();
    void Create(T entity);
    void Update(T entity);
}
