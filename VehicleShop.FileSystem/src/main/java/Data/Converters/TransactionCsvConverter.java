package Data.Converters;

import Data.Entities.Accounting.Enums.TransactionType;
import Data.Entities.Accounting.Transaction;
import com.opencsv.CSVParser;

import java.time.LocalDateTime;

public class TransactionCsvConverter
    implements IEntityConverter<Transaction, String>
{

    @Override
    public Transaction ConvertReverse(String s)
    {
        CSVParser parser = new CSVParser();
        String[] csvList;
        try {
            csvList = parser.parseLine(s);
            int id = Integer.parseInt(csvList[0]);
            TransactionType transactionType = TransactionType.valueOf(csvList[1]);
            String productType = csvList[2];
            int productId = Integer.parseInt(csvList[3]);
            String productName = csvList[4];
            double salePrice = Double.parseDouble(csvList[5]);
            int customerId = Integer.parseInt(csvList[6]);
            String customerName = csvList[7];
            LocalDateTime date = LocalDateTime.parse(csvList[8]);

            return new Transaction(id, transactionType, productType, productId, productName, salePrice, customerId, customerName, date);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String Convert(Transaction t) {
        return String.format("\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\"",
                t.get_key(),
                t.get_transactionType(),
                t.get_productType(),
                t.get_productId(),
                t.get_productName(),
                t.get_salePrice(),
                t.get_customerId(),
                t.get_customerFIO(),
                t.get_date());
    }
}
