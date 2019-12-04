package Data.Converters;

import Data.Entities.Accounting.Customer;
import com.opencsv.CSVParser;

public class CustomerCsvConverter implements IEntityConverter<Customer, String> {
    @Override
    public Customer ConvertReverse(String csv) {
        CSVParser parser = new CSVParser();
        String[] csvList;
        try {
            csvList = parser.parseLine(csv);
            int id = Integer.parseInt(csvList[0]);
            String name = csvList[1];
            String birthday = csvList[2];
            return new Customer(id, name, birthday);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String Convert(Customer customer) {
        return String.format("\"%s\",\"%s\",\"%s\"", customer.get_key(), customer.get_name(), customer.get_DOB());
    }
}
