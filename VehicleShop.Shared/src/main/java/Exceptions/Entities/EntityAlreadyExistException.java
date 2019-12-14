package Exceptions.Entities;

public class EntityAlreadyExistException
        extends Exception
{
    public EntityAlreadyExistException() {}

    public EntityAlreadyExistException(String key, String entityType)
    {
        super(String.format("%s with key '%s' already exists!", entityType, key));
    }
}