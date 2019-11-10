package Data.Repository;

import Data.Entities.IEntity;
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
    public InMemoryRepositoryBase(List<T> collection)
    {
        _collection = collection;
    }

    protected List<T> _collection;

    @Override
    public T Get(TKey key) throws EntityNotFoundException {
        var entity = _collection.stream().filter(i -> key.equals(i.get_key()))
                .findAny()
                .orElse(null);

        if(entity == null)
        {
            throw new EntityNotFoundException();
        }

        return entity;
    }

    @Override
    public List<T> List() {
        return _collection;
    }

    @Override
    public void Create(T entity) {
        T existentEntity = _collection.stream().filter(i -> entity.get_key().equals(i.get_key()))
                .findAny()
                .orElse(null);

        if(existentEntity == null)
        {
            _collection.add(entity);
        }
        //todo: throw entity already exists exception
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
        }

        throw new EntityNotFoundException();
    }
}
