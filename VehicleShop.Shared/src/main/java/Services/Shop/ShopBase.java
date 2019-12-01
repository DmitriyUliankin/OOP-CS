package Services.Shop;

import Data.Entities.Accounting.Customer;
import Data.Entities.Accounting.Enums.SaleableStatus;
import Data.Entities.Accounting.Enums.TransactionType;
import Data.Entities.Accounting.ISaleable;
import Data.Entities.Accounting.SaleDetails;
import Data.Entities.Accounting.Transaction;
import Data.Entities.IEntity;
import Data.Repository.IEntityRepository;
import Exceptions.Entities.EntityAlreadyExistException;
import Exceptions.Entities.EntityNotFoundException;
import Exceptions.Entities.ProductAlreadySoldException;
import Services.Accounting.IAccountingService;
import Services.Shop.Models.ProductListItem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

public abstract class ShopBase<TEntity extends ISaleable & IEntity<TKey>, TKey>
    implements IShop<TEntity, TKey>
{
    protected IAccountingService _accountingService;
    protected IEntityRepository<TKey,TEntity> _entityRepository;

    public ShopBase(IAccountingService accountingService, IEntityRepository<TKey,TEntity> entityRepository)
    {
        _accountingService = accountingService;
        _entityRepository = entityRepository;
    }

    protected abstract ProductListItem ToListItem(TEntity entity);

    public ArrayList<ProductListItem> List()
    {
        var items = _entityRepository.List()
                .stream()
                .filter(x -> x.get_status() == SaleableStatus.InStock)
                .map(this::ToListItem)
                .collect(Collectors.toList());

        return new ArrayList<>(items);
    }

    public TEntity Get(TKey key) throws EntityNotFoundException {
        return _entityRepository.Get(key);
    }

    public void AddToStock(TEntity item) throws EntityAlreadyExistException {
        _entityRepository.Create(item);
    }

    public void Sale(TKey key, int customerId, String customerName, double price) throws EntityNotFoundException, ProductAlreadySoldException {
        TEntity item = _entityRepository.Get(key);
        if(item.get_status() == SaleableStatus.Sold)
        {
            throw new ProductAlreadySoldException(key.toString(), item.get_productType());
        }
        SaleDetails details = item.Sale(price);
        Transaction transaction = new Transaction(TransactionType.Sale, details, customerId, customerName, LocalDateTime.now());
        _accountingService.WriteTransaction(transaction);
        _entityRepository.Update(item);
    }

    public void Sale(TKey key, Customer customer) throws EntityNotFoundException, ProductAlreadySoldException {
        TEntity item = _entityRepository.Get(key);
        Sale(key, customer, item.get_price());
    }

    public void Sale(TKey key, Customer customer, double price) throws EntityNotFoundException, ProductAlreadySoldException {
        Sale(key, customer.get_key(), customer.get_name(), price);
    }

    public void SetPrice(TKey key, double newPrice) throws EntityNotFoundException {
        TEntity item = _entityRepository.Get(key);
        item.set_price(newPrice);
        _entityRepository.Update(item);
    }

    public void RemoveFromStock(TKey key) throws EntityNotFoundException {
        TEntity item = _entityRepository.Get(key);
        item.Sale(item.get_price());
        _entityRepository.Update(item);
    }
}
