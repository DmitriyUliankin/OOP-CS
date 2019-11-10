package Data.Entities.Accounting;

import Data.Entities.Accounting.Enums.SaleableStatus;

public interface ISaleable {
    double get_Price();
    void set_Price(double price);
    SaleableStatus get_Status();
    SaleDetails MarkSold(double salePrice);
    String get_productType();
}
