package Data.Entities.Vehicle;

import Data.Entities.Accounting.Enums.SaleableStatus;
import Data.Entities.Accounting.ISaleable;
import Data.Entities.Accounting.SaleDetails;
import Data.Entities.IEntity;
import Data.Entities.Vehicle.Enums.FuelType;
import lombok.Getter;
import lombok.Setter;

public abstract class Vehicle
        implements ISaleable, IEntity<Integer>
{
    public Vehicle(int serialNumber, String name, int year, FuelType fuelType, double price)
    {
        _serialNumber = serialNumber;
        _name = name;
        _year = year;
        _fuelType = fuelType;
        _price = price;
    }

    public Vehicle(int serialNumber, String name, int year, FuelType fuelType, double price, SaleableStatus status)
    {
        _serialNumber = serialNumber;
        _name = name;
        _year = year;
        _fuelType = fuelType;
        _price = price;
        _status = status;
    }

    private @Getter int _serialNumber;
    private @Getter @Setter String _name;
    private @Getter int _year;
    private @Getter FuelType _fuelType;
    private @Getter @Setter double _price;
    private @Getter SaleableStatus _status = SaleableStatus.InStock;

    public SaleDetails MarkSold(double salePrice)
    {
        _price = salePrice;
        _status = SaleableStatus.Sold;
        return new SaleDetails(get_productType(), _serialNumber, _name, salePrice);
    }

    public Integer get_key()
    {
        return _serialNumber;
    }
}
