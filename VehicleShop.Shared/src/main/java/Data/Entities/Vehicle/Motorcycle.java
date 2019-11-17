package Data.Entities.Vehicle;

import Data.Entities.Accounting.Enums.SaleableStatus;
import Data.Entities.Vehicle.Enums.FuelType;

public class Motorcycle extends Vehicle {
    private final String Type = "Motorcycle";

    public Motorcycle(int serialNumber, String name, int year, FuelType fuelType, double price) {
        super(serialNumber, name, year, fuelType, price);
    }

    public Motorcycle(int serialNumber, String name, int year, FuelType fuelType, double price, SaleableStatus status) {
        super(serialNumber, name, year, fuelType, price,status);
    }
    public String get_productType() {
        return Type;
    }
}
