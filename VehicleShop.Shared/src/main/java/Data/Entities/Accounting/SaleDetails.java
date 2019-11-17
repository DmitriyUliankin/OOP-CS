package Data.Entities.Accounting;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class SaleDetails {
    public @Getter String _productType;
    public @Getter int _productId;
    public @Getter String _productName;
    public @Getter double _salePrice;
}
