package Services.Shop;

import Data.Entities.Accounting.Customer;
import Data.Entities.Accounting.ISaleable;

public interface IShop <T extends ISaleable>
{
    void AddToStock(T item);
    void Sale(T item, int customerId, String customerName, double price);
    void Sale(T item, Customer customer);
    void Sale(T item, Customer customer, double price);
    void SaleAnonymously(T item);
    void SaleAnonymously(T item, double price);
    void FixPrice(T item, double pricePercentage);
    void SetPrice(T item, double newPrice);
    void RemoveFromStock(T item);
}
