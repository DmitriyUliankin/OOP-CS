package Data.Converters;

import Data.Entities.Accounting.Customer;

public class CustomerCsvConverter implements IEntityConverter<Customer, String> {
    @Override
    public Customer ConvertReverse(String csv) {
        String[] csvList = csv.split(",");
        int id = Integer.parseInt(csvList[0]);
        String name = csvList[1];
        String birthday = csvList[2];
        return new Customer(id, name, birthday);
    }

    @Override
    public String Convert(Customer customer) {
        return String.format("%s,%s,%s", customer.get_key(), customer.get_name(), customer.get_DOB());
    }
}
