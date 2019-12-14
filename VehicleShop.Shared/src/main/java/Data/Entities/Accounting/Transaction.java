package Data.Entities.Accounting;

import Data.Entities.Accounting.Enums.TransactionType;
import Data.Entities.IEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
public class Transaction
    implements IEntity<Integer>
{
    public Transaction(TransactionType transactionType, String productType, int productId, String productName, double salePrice, int customerId, String customerFIO, LocalDateTime date)
    {
        _transactionType = transactionType;
        _productType = productType;
        _productId = productId;
        _productName = productName;
        _salePrice = salePrice;
        _customerId = customerId;
        _customerFIO = customerFIO;
        _date = date;
    }
    public Transaction(TransactionType transactionType, SaleDetails saleDetails, int customerId, String customerFIO, LocalDateTime date)
    {
        this(transactionType, saleDetails.get_productType(), saleDetails.get_productId(), saleDetails.get_productName(), saleDetails.get_salePrice(), customerId, customerFIO, date);
    }
    public Transaction(int id, TransactionType transactionType, SaleDetails saleDetails, int customerId, String customerFIO, LocalDateTime date)
    {
        this(transactionType, saleDetails.get_productType(), saleDetails.get_productId(), saleDetails.get_productName(), saleDetails.get_salePrice(), customerId, customerFIO, date);
        _id = id;
    }

    private @Setter int _id;
    private @Getter TransactionType _transactionType;
    private @Getter String _productType;
    private @Getter int _productId;
    private @Getter String _productName;
    private @Getter double _salePrice;
    private @Getter int _customerId;
    private @Getter String _customerFIO;
    private @Getter LocalDateTime _date;

    public Integer get_key() {
        return _id;
    }
}
