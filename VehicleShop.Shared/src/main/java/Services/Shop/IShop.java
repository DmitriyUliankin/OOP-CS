package Services.Shop;

import Data.Entities.Accounting.Customer;
import Data.Entities.Accounting.ISaleable;
import Data.Entities.IEntity;
import Exceptions.Entities.EntityNotFoundException;
import Services.Shop.Models.ProductListItem;

import java.util.ArrayList;

public interface IShop <TEntity extends ISaleable & IEntity<TKey>, TKey>
{
    ArrayList<ProductListItem> List();
    TEntity Get(TKey key) throws EntityNotFoundException;

    void AddToStock(TEntity item);

    void Sale(TEntity item, int customerId, String customerName, double price) throws EntityNotFoundException;
    void Sale(TEntity item, Customer customer) throws EntityNotFoundException;
    void Sale(TEntity item, Customer customer, double price) throws EntityNotFoundException;
    void SaleAnonymously(TEntity item) throws EntityNotFoundException;
    void SaleAnonymously(TEntity item, double price) throws EntityNotFoundException;

    void FixPrice(TEntity item, double pricePercentage) throws EntityNotFoundException;
    void SetPrice(TEntity item, double newPrice) throws EntityNotFoundException;

    void RemoveFromStock(TEntity item) throws EntityNotFoundException;
}
