package Data.Entities.Accounting;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class SaleDetails {
    private @Getter String _productType;
    private @Getter int _productId;
    private @Getter String _productName;
    private @Getter double _salePrice;
}
