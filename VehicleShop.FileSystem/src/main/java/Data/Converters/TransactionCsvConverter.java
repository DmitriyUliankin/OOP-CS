package Data.Converters;

import Data.Entities.Accounting.Enums.TransactionType;
import Data.Entities.Accounting.Transaction;

import java.time.LocalDateTime;

public class TransactionCsvConverter
    implements IEntityConverter<Transaction, String>
{

    @Override
    public Transaction ConvertReverse(String s)
    {
        String[] csv = s.split(",");

        int id = Integer.parseInt(csv[0]);
        TransactionType transactionType = TransactionType.valueOf(csv[1]);
        String productType = csv[2];
        int productId = Integer.parseInt(csv[3]);
        String productName = csv[4];
        double salePrice = Double.parseDouble(csv[5]);
        int customerId = Integer.parseInt(csv[6]);
        String customerName = csv[7];
        LocalDateTime date = LocalDateTime.parse(csv[8]);

        return new Transaction(id, transactionType, productType, productId, productName, salePrice, customerId, customerName, date);
    }

    @Override
    public String Convert(Transaction t) {
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s",
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
