package Services.Shop.Vehicle;

import Data.Entities.Vehicle.Car;
import Data.Repository.IEntityRepository;
import Services.Accounting.IAccountingService;
import Services.Shop.Models.ProductListItem;
import Services.Shop.ShopBase;

public class CarShop
    extends ShopBase<Car, Integer>
{
    public CarShop(IAccountingService accountingService, IEntityRepository<Integer, Car> carRepository) {
        super(accountingService, carRepository);
    }

    @Override
    protected ProductListItem ToListItem(Car car) {
        return new ProductListItem(car.get_key(), car.get_productType(),car.get_name(),car.get_price());
    }
}
