package Services.Accounting;

import Data.Entities.Accounting.Transaction;

public interface IAccountingService {
    void WriteTransaction(Transaction transaction);
}
