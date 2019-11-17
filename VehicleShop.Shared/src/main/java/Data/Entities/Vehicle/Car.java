package Data.Entities.Vehicle;

import Data.Entities.Vehicle.Enums.FuelType;

public class Car
    extends  Vehicle
{
    public Car(int _serialNumber, String _name, int _year, FuelType _fuelType, double _price) {
        super(_serialNumber, _name, _year, _fuelType, _price);
    }

    private final String Type = "Car";

    public String get_productType()
    {
        return Type;
    }
}
