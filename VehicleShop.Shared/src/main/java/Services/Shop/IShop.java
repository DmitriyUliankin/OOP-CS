package Services.Shop;

import Data.Entities.Accounting.Customer;
import Data.Entities.Accounting.ISaleable;
import Data.Entities.IEntity;
import Exceptions.Entities.EntityAlreadyExistException;
import Exceptions.Entities.EntityNotFoundException;
import Exceptions.Entities.ProductAlreadySoldException;
import Services.Shop.Models.ProductListItem;

import java.util.ArrayList;

public interface IShop <TEntity extends ISaleable & IEntity<TKey>, TKey>
{
    ArrayList<ProductListItem> List();
    TEntity Get(TKey key) throws EntityNotFoundException;

    void AddToStock(TEntity item) throws EntityAlreadyExistException;

    void Sale(TKey key, int customerId, String customerName, double price) throws EntityNotFoundException, ProductAlreadySoldException;
    void Sale(TKey key, Customer customer) throws EntityNotFoundException, ProductAlreadySoldException;
    void Sale(TKey key, Customer customer, double price) throws EntityNotFoundException, ProductAlreadySoldException;

    void SetPrice(TKey key, double newPrice) throws EntityNotFoundException;

    void RemoveFromStock(TKey key) throws EntityNotFoundException;
}
