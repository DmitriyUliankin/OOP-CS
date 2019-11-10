package Services.Shop;

import Data.Entities.Accounting.Customer;
import Data.Entities.Accounting.Enums.SaleableStatus;
import Data.Entities.Accounting.Enums.TransactionType;
import Data.Entities.Accounting.ISaleable;
import Data.Entities.Accounting.SaleDetails;
import Data.Entities.Accounting.Transaction;
import Data.Repository.IEntityRepository;
import Services.Accounting.IAccountingService;

import java.util.Date;

public abstract class ShopBase<T extends ISaleable>
    implements IShop<T>
{
    public ShopBase(IAccountingService accountingService, IEntityRepository<T> entityRepository)
    {
        _accountingService = accountingService;
        _entityRepository = entityRepository;
    }

    protected IAccountingService _accountingService;
    protected IEntityRepository<T> _entityRepository;

    public void AddToStock(T item) {
        _entityRepository.Create(item);
    }

    public void Sale(T item, int customerId, String customerName, double price)
    {
        if(item.get_status() == SaleableStatus.Sold)
        {
            //todo: throw new item already sold exception
        }
        SaleDetails details = item.MarkSold(price);
        Transaction transaction = new Transaction(TransactionType.Sale, details, customerId, customerName, new Date());
        _accountingService.WriteTransaction(transaction);
        _entityRepository.Update(item);
    }

    public void Sale(T item, Customer customer) {
        Sale(item, customer, item.get_price());
    }

    public void Sale(T item, Customer customer, double price) {
        Sale(item, customer.get_id(), customer.get_name(), price);
    }

    public void SaleAnonymously(T item) {
        SaleAnonymously(item, item.get_price());
    }

    public void SaleAnonymously(T item, double price)
    {
        Sale(item, -1, null, price);
    }

    public void FixPrice(T item, double pricePercentage) {
        SetPrice(item, item.get_price() * pricePercentage / 100);
        _entityRepository.Update(item);
    }

    public void SetPrice(T item, double newPrice) {
        item.set_price(newPrice);
        _entityRepository.Update(item);
    }

    public void RemoveFromStock(T item) {
        item.MarkSold(item.get_price());
        _entityRepository.Update(item);
    }
}
