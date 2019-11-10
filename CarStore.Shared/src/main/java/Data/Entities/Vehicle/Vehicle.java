package Data.Entities.Vehicle;

import Data.Entities.Accounting.ISaleable;
import Data.Entities.Accounting.SaleDetails;
import Data.Entities.Vehicle.Enums.FuelType;
import Data.Entities.Accounting.Enums.SaleableStatus;
import lombok.Getter;
import lombok.Setter;

public abstract class Vehicle
    implements ISaleable
{
    public Vehicle(int serialNumber, String name, int year, FuelType fuelType, double price)
    {
        _serialNumber = serialNumber;
        _name = name;
        _year = year;
        _fuelType = fuelType;
        _price = price;
    }

    private @Getter int _serialNumber;
    private @Getter @Setter String _name;
    private @Getter int _year;
    private @Getter FuelType _fuelType;
    private double _price;
    private SaleableStatus _status = SaleableStatus.InStock;

    public double get_Price() {
        return _price;
    }

    public void set_Price(double price){
        _price = price;
    }

    public SaleableStatus get_Status() {
        return _status;
    }

    public SaleDetails MarkSold(double salePrice)
    {
        _price = salePrice;
        _status = SaleableStatus.Sold;
        return new SaleDetails(get_productType(), _serialNumber, _name, salePrice);
    }
}
