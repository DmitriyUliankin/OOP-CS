package Data.Entities.Accounting;

import Data.Entities.Accounting.Enums.TransactionType;
import lombok.Getter;

import java.util.Date;

public class Transaction {

    public Transaction(TransactionType transactionType, SaleDetails saleDetails, int customerId, String customerFIO, Date date)
    {
        _transactionType = transactionType;
        _saleDetails = saleDetails;
        _customerId = customerId;
        _customerFIO = customerFIO;
        _date = date;
    }

    private @Getter int _id;
    private @Getter TransactionType _transactionType;
    private @Getter SaleDetails _saleDetails;
    private @Getter int _customerId;
    private @Getter String _customerFIO;
    private @Getter Date _date;
}
