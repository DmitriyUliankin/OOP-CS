package Repositories;

import Data.Converters.CustomerCsvConverter;
import Data.Converters.IEntityConverter;
import Data.Entities.Accounting.Customer;
import Data.Repository.FileRepositoryBase;
import lombok.NonNull;

public class CustomerCsvRepository extends FileRepositoryBase<Integer, Customer> {


    public CustomerCsvRepository(@NonNull String filepath) {
        super(filepath);
    }

    @Override
    protected IEntityConverter<Customer, String> get_converter() {
        return new CustomerCsvConverter();
    }
}
