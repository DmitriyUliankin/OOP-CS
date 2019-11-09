package Services.Shop.Vehicle;

import Data.Entities.Vehicle.Car;
import Data.Repository.IEntityRepository;
import Services.Accounting.IAccountingService;
import Services.Shop.ShopBase;

public class CarShop
    extends ShopBase<Car>
{
    public CarShop(IAccountingService accountingService, IEntityRepository<Car> carRepository) {
        super(accountingService, carRepository);
    }
}
