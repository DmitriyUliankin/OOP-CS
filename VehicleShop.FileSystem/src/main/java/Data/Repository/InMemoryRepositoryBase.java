package Data.Repository;

import Data.Entities.IEntity;
import Exceptions.Entities.EntityAlreadyExistException;
import Exceptions.Entities.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class InMemoryRepositoryBase<TKey, T extends IEntity<TKey>>
    implements IEntityRepository<TKey, T>
{
    public InMemoryRepositoryBase()
    {
        _collection = new ArrayList<>();
    }

    protected List<T> _collection;
    protected String _entityName = "Entity";

    @Override
    public T Get(TKey key) throws EntityNotFoundException {
        T entity = _collection.stream().filter(i -> key.equals(i.get_key()))
                .findAny()
                .orElse(null);

        if(entity == null)
        {
            throw new EntityNotFoundException(key.toString(), _entityName);
        }

        return entity;
    }

    @Override
    public List<T> List() {
        return _collection;
    }

    @Override
    public void Create(T entity) throws EntityAlreadyExistException {
        T existentEntity = _collection.stream().filter(i -> entity.get_key().equals(i.get_key()))
                .findAny()
                .orElse(null);

        if(existentEntity == null)
        {
            _collection.add(entity);
        }
        else{
            throw new EntityAlreadyExistException(entity.get_key().toString(), _entityName);
        }
    }

    @Override
    public void Update(T entity) throws EntityNotFoundException {
        T existentEntity = _collection.stream().filter(i -> entity.get_key().equals(i.get_key()))
                .findAny()
                .orElse(null);

        if(existentEntity != null)
        {
            int index = _collection.indexOf(existentEntity);
            _collection.set(index, entity);
            return;
        }

        throw new EntityNotFoundException(entity.get_key().toString(), _entityName);
    }
}
