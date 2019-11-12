package Data.Entities.Vehicle;

import Data.Entities.Vehicle.Enums.FuelType;

public class Motorcycle extends Vehicle {
    private final String Type = "Motorcycle";

    public Motorcycle(int serialNumber, String name, int year, FuelType fuelType, double price) {
        super(serialNumber, name, year, fuelType, price);
    }

    public String get_productType() {
        return Type;
    }
}
