package Services.Shop;

import Data.Entities.Accounting.Customer;
import Data.Entities.Accounting.Enums.SaleableStatus;
import Data.Entities.Accounting.Enums.TransactionType;
import Data.Entities.Accounting.ISaleable;
import Data.Entities.Accounting.SaleDetails;
import Data.Entities.Accounting.Transaction;
import Data.Entities.IEntity;
import Data.Repository.IEntityRepository;
import Exceptions.Entities.EntityNotFoundException;
import Services.Accounting.IAccountingService;

import java.util.Date;

public abstract class ShopBase<TEntity extends ISaleable & IEntity<TKey>, TKey>
    implements IShop<TEntity, TKey>
{
    public ShopBase(IAccountingService accountingService, IEntityRepository<TKey,TEntity> entityRepository)
    {
        _accountingService = accountingService;
        _entityRepository = entityRepository;
    }

    protected IAccountingService _accountingService;
    protected IEntityRepository<TKey,TEntity> _entityRepository;

    public void AddToStock(TEntity item) {
        _entityRepository.Create(item);
    }

    public void Sale(TEntity item, int customerId, String customerName, double price) throws EntityNotFoundException {
        if(item.get_status() == SaleableStatus.Sold)
        {
            //todo: throw new item already sold exception
        }
        SaleDetails details = item.MarkSold(price);
        Transaction transaction = new Transaction(TransactionType.Sale, details, customerId, customerName, new Date());
        _accountingService.WriteTransaction(transaction);
        _entityRepository.Update(item);
    }

    public void Sale(TEntity item, Customer customer) throws EntityNotFoundException {
        Sale(item, customer, item.get_price());
    }

    public void Sale(TEntity item, Customer customer, double price) throws EntityNotFoundException {
        Sale(item, customer.get_key(), customer.get_name(), price);
    }

    public void SaleAnonymously(TEntity item) throws EntityNotFoundException {
        SaleAnonymously(item, item.get_price());
    }

    public void SaleAnonymously(TEntity item, double price) throws EntityNotFoundException {
        Sale(item, -1, null, price);
    }

    public void FixPrice(TEntity item, double pricePercentage) throws EntityNotFoundException {
        SetPrice(item, item.get_price() * pricePercentage / 100);
        _entityRepository.Update(item);
    }

    public void SetPrice(TEntity item, double newPrice) throws EntityNotFoundException {
        item.set_price(newPrice);
        _entityRepository.Update(item);
    }

    public void RemoveFromStock(TEntity item) throws EntityNotFoundException {
        item.MarkSold(item.get_price());
        _entityRepository.Update(item);
    }
}
