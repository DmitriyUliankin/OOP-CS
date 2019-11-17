package Data.Repository.Csv;

import Data.Converters.IEntityConverter;
import Data.Entities.Accounting.Transaction;
import Data.Repository.FileRepositoryBase;
import lombok.NonNull;

public class TransactionCsvRepository
        extends FileRepositoryBase<Integer, Transaction>
{
    public TransactionCsvRepository(@NonNull String filepath, @NonNull IEntityConverter<Transaction, String> converter) {
        super(filepath, converter);
    }
}
