package Services.Shop.Vehicle;

import Data.Entities.Vehicle.Motorcycle;
import Data.Repository.IEntityRepository;
import Services.Accounting.IAccountingService;
import Services.Shop.Models.ProductListItem;
import Services.Shop.ShopBase;

public class MotorcycleShop
        extends ShopBase<Motorcycle, Integer> {
    public MotorcycleShop(IAccountingService accountingService, IEntityRepository<Integer, Motorcycle> motorcycleRepository) {
        super(accountingService, motorcycleRepository);
    }

    @Override
    protected ProductListItem ToListItem(Motorcycle motorcycle) {
        return new ProductListItem(motorcycle.get_key(), motorcycle.get_productType(), motorcycle.get_name(), motorcycle.get_price());
    }
}
