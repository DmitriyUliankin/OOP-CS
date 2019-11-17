package Services.Accounting;

import Data.Entities.Accounting.Transaction;
import Data.Repository.IEntityRepository;

public class AccountingService
    implements IAccountingService
{
    public AccountingService(IEntityRepository<Integer, Transaction> transactionRepository)
    {
        _transactionRepository = transactionRepository;
    }

    private IEntityRepository<Integer, Transaction> _transactionRepository;

    @Override
    public void WriteTransaction(Transaction transaction) {
        _transactionRepository.Create(transaction);
    }
}
