import Data.Entities.Accounting.Transaction;
import Data.Entities.Vehicle.Car;
import Data.Entities.Vehicle.Enums.FuelType;
import Data.Entities.Vehicle.Motorcycle;
import Data.Entities.Vehicle.Vehicle;
import Exceptions.Entities.EntityNotFoundException;
import Services.Shop.Models.ProductListItem;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    private boolean _isActive = true;

    public boolean getStatus()
    {
        return _isActive;
    }

    public void printMenu() {
        System.out.println("1. Add vehicle");
        System.out.println("2. Delete vehicle");
        System.out.println("3. Show all vehicles");
        System.out.println("4. Show all cars");
        System.out.println("5. Show all motorcycles");
        System.out.println("6. Get vehicle details");
        System.out.println("7. Sale vehicle");
        System.out.println("8. Print transactions by day");
        System.out.println("9. Save to storage");
        System.out.println("10. Get from storage");
        System.out.println("11. Exit");
        waitUserInput();
    }

    private void waitUserInput() {
        int input = 0; //todo: get user input
        switch (input)
        {
            case 1: addVehicle();
            case 2: deleteVehicle();
            case 3: showAllVehicles();
            case 4: showCars();
            //todo: add other options
            case 6: getVehicleDetails();
            //todo: add other options
            case 8: printTodaysTransaction();
            //todo: add other options
            case 11: exit();
            default: System.out.println("Incorrect input!");
        }
    }

    private int getVehicleType() {
        System.out.println("Choose: \"1\" - Car or \"2\" - motorcycle...");
        int vehicleType = 0; //todo: get user input
        if(vehicleType != 1 && vehicleType != 2)
        {
            System.out.println("Incorrect input!");
        }
        return vehicleType;
    }

    private int getEntityId() {
        System.out.println("Enter ID...");
        int input = 0; //todo: get user input
        if(input < 1)
        {
            System.out.println("Incorrect input!");
        }
        return input;
    }

    private void addVehicle()
    {
        int vehicleType = getVehicleType();

        System.out.println("Enter serial number (>0): ");
        int serialNumber = 0; //todo: get user input + check that value is > 0

        System.out.println("Enter name: ");
        String name = null; //todo: get user input

        System.out.println("Enter year: ");
        int year = 0; //todo: get user input

        System.out.println("Enter fuel type (0 = Petroleum, 1 = ...): ");
        FuelType fuelType = FuelType.Petroleum; //todo: get user input + check that value is in valid range

        System.out.println("Enter price: ");
        double price = 0; //todo: get user input

        if(vehicleType == 1)
        {
            Car car = new Car(serialNumber, name, year, fuelType, price);
            ShopService.get_carShop().AddToStock(car);
        }
        else if(vehicleType == 2)
        {
            Motorcycle motorcycle = new Motorcycle(serialNumber, name, year, fuelType, price);
            ShopService.get_motorcycleShop().AddToStock(motorcycle);
        }
    }

    private void deleteVehicle() {
        int vehicleType = getVehicleType();
        int id = getEntityId();
        if(vehicleType == 1)
        {
            try
            {
                Car car = ShopService.get_carShop().Get(id);
                ShopService.get_carShop().RemoveFromStock(car);
                return;
            }
            catch (EntityNotFoundException e) { }
        }
        else if(vehicleType == 2)
        {
            try
            {
                Motorcycle motorcycle = ShopService.get_motorcycleShop().Get(id);
                ShopService.get_motorcycleShop().AddToStock(motorcycle);
                return;
            }
            catch (EntityNotFoundException e) { }
        }
        System.out.println("Product not found!");
    }

    private void showAllVehicles() {
        ArrayList<ProductListItem> products = ShopService.listAllVehicles();
        printProductList(products);
    }
    private void showCars() {
        ArrayList<ProductListItem> products = ShopService.get_carShop().List();
        printProductList(products);
    }

    private void printProductList(ArrayList<ProductListItem> products) {
        System.out.println("ID\tType\tName\tPrice");

        for (ProductListItem p : products) {
            System.out.println(String.format("%s\t%s\t%s\t%s", p.get_id(), p.get_type(), p.get_name(), p.get_price()));
        }
    }

    private void getVehicleDetails()
    {
        int type = getVehicleType();
        int id = getEntityId();

        if(type == 1)
        {
            try {
                Car car = ShopService.get_carShop().Get(id);
                printVehicle(car);
            } catch (EntityNotFoundException e) {
                System.out.println("Product not found!");
            }
        }
        else if(type == 2)
        {
            try {
                Motorcycle moto = ShopService.get_motorcycleShop().Get(id);
                printVehicle(moto);
            } catch (EntityNotFoundException e) {
                System.out.println("Product not found!");
            }
        }
    }

    private void printVehicle(Vehicle v) {
        System.out.println(String.format("Type: %s", v.get_productType()));
        System.out.println(String.format("Serial number: %s", v.get_serialNumber()));
        System.out.println(String.format("Year: %s", v.get_year()));
        System.out.println(String.format("Fuel type: %s", v.get_fuelType().toString()));
        System.out.println(String.format("Status: %s", v.get_status().toString()));
        System.out.println(String.format("Price: %s", v.get_price()));
    }

    private void printTodaysTransaction() {
        List<Transaction> sales = ShopService.listTodaysTransactions();
        for (Transaction t: sales) {
            System.out.println(String.format("Transaction type: %s", t.get_transactionType().toString()));
            System.out.println(String.format("Product type: %s", t.get_productType()));
            System.out.println(String.format("Product id: %s", t.get_productId()));
            System.out.println(String.format("Product name: %s", t.get_productName()));
            System.out.println(String.format("Price: %s", t.get_salePrice()));
            System.out.println(String.format("Customer: %s", t.get_customerFIO()));
            System.out.println(String.format("Date: %s", t.get_date().toString()));
        }
    }

    private void sale_vehicle_to_customer() {

    }

    private void sales_by_day() {

    }

    private void add_to_storage() {

    }

    private void get_from_storage() {

    }

    private void exit()
    {
        _isActive = false;
    }
}
