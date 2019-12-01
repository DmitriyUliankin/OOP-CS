package Exceptions.Entities;

public class EntityNotFoundException
    extends Exception
{
    public EntityNotFoundException() {}

    public EntityNotFoundException(String key, String entityType)
    {
        super(String.format("%s '%s' not found!", entityType, key));
    }
}