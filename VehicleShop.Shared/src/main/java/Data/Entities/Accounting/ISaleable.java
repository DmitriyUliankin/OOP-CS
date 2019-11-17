package Data.Entities.Accounting;

import Data.Entities.Accounting.Enums.SaleableStatus;

public interface ISaleable {
    double get_price();
    void set_price(double price);
    SaleableStatus get_status();
    SaleDetails Sale(double salePrice);
    String get_productType();
}
