import Services.IShopService;
import Services.Shop.Models.ProductListItem;
import Services.Shop.Vehicle.CarShop;
import Services.Shop.Vehicle.MotorcycleShop;

import java.util.ArrayList;

public class Menu implements IShopService {

    public void printMenu() {
        System.out.println("1. Add vehicle");
        System.out.println("2. Delete vehicle");
        System.out.println("3. Show all vehicle");
        System.out.println("4. Show all cars");
        System.out.println("5. Show all Motorcycles");
        System.out.println("6. Sale a vehicle to a customer");
        System.out.println("7. Print sales by day");
        System.out.println("8. Save to storage");
        System.out.println("9. Get from storage");
    }

    public void addCar() {
        System.out.println("Enter serial number: ");
        System.out.println("Enter name: ");
        System.out.println("Enter year: ");
        System.out.println("Enter fuel type: ");
        System.out.println("Enter price: ");
    }

    public void addMotorcycle() {
        System.out.println("Enter serial number: ");
        System.out.println("Enter name: ");
        System.out.println("Enter year: ");
        System.out.println("Enter fuel type: ");
        System.out.println("Enter price: ");
    }

    public void deleteCar() {

    }

    public void deleteMotorcycle() {

    }

    @Override
    public CarShop get_carShop() {
        return null;
    }

    @Override
    public MotorcycleShop get_motorcycleShop() {
        return null;
    }

    @Override
    public ArrayList<ProductListItem> ListAllVehilcles() {
        return null;
    }

    public void sale_vehicle_to_customer() {

    }

    public void sales_by_day() {

    }

    public void add_to_storage() {

    }

    public void get_from_storage() {

    }
}
