package Data.Entities.Vehicle;

import Data.Entities.Accounting.Enums.SaleableStatus;
import Data.Entities.Vehicle.Enums.FuelType;

public class Car
        extends Vehicle {
    private final String Type = "Car";

    public Car(int _serialNumber, String _name, int _year, FuelType _fuelType, double _price) {
        super(_serialNumber, _name, _year, _fuelType, _price);
    }

    public Car(int serialNumber, String name, int year, FuelType fuelType, double price, SaleableStatus status) {
        super(serialNumber, name, year, fuelType, price,status);
    }

    public String get_productType() {
        return Type;
    }
}
