package Exceptions.Entities;

public class ProductAlreadySoldException
        extends Exception
{
    public ProductAlreadySoldException() {}

    public ProductAlreadySoldException(String key, String entityType)
    {
        super(String.format("%s '%s' already sold!", entityType, key));
    }
}
