package Repositories;

import Data.Converters.IEntityConverter;
import Data.Converters.TransactionCsvConverter;
import Data.Entities.Accounting.Transaction;
import Data.Repository.FileRepositoryBase;
import lombok.NonNull;

public class TransactionCsvRepository extends FileRepositoryBase<Integer, Transaction> {
    private IEntityConverter<Transaction, String> _converter;

    public TransactionCsvRepository(@NonNull String filepath) {
        super(filepath);
        _converter = new TransactionCsvConverter();
    }

    @Override
    protected IEntityConverter<Transaction, String> get_converter() {
        return _converter;
    }
}
