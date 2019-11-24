import Data.Entities.Accounting.Customer;
import Data.Entities.Accounting.Transaction;
import Data.Entities.Vehicle.Car;
import Data.Entities.Vehicle.Enums.FuelType;
import Data.Entities.Vehicle.Motorcycle;
import Data.Entities.Vehicle.Vehicle;
import Data.Repository.FileRepositoryBase;
import Exceptions.Entities.EntityNotFoundException;
import Services.Shop.Models.ProductListItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
        System.out.println("9. Exit");
        waitUserInput();
    }

    private void waitUserInput() {
        int input = 0;
        Scanner scanner = new Scanner(System.in);
        input = scanner.nextInt();
        switch (input)
        {
            case 1: {
                addVehicle();
                break;
            }
            case 2: {
                deleteVehicle();
                break;
            }
            case 3: {
                showAllVehicles();
                break;
            }
            case 4: {
                showCars();
                break;
            }
            case 5: {
                showMotorcycles();
                break;
            }
            case 6: {
                getVehicleDetails();
                break;
            }
            case 7: {
                saleVehicleToCustomer();
                break;
            }
            case 8: {
                printTodaysTransaction();
                break;
            }
            case 9: {
                exit();
            }
            default: {
                System.out.println("Incorrect input!");
            }
        }
    }

    private int getVehicleType() {
        System.out.println("Choose: \"1\" - Car or \"2\" - Motorcycle");
        int vehicleType = 0;
        Scanner scanner = new Scanner(System.in);
        while (true){
            vehicleType = scanner.nextInt();
            if(vehicleType > 0 && vehicleType < 3) {
                break;
            } else {
                System.out.println("Incorrect input!");
            }
        }
        return vehicleType;
    }

    private int getEntityId() {
        System.out.println("Enter ID");
        int input = 0;
        Scanner scanner = new Scanner(System.in);
        while(true){
            input = scanner.nextInt();
            if(input > 0) {
                break;
            } else {
                System.out.println("Incorrect input!");
            }
        }
        return input;
    }

    private void addVehicle()
    {
        int vehicleType = getVehicleType();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter serial number (>0): ");
        int serialNumber = 0;
        while(true){
            serialNumber = scanner.nextInt();
            if(serialNumber > 0) {
                break;
            }
            else {
                System.out.println("Incorrect input!");
            }
        }

        System.out.println("Enter name: ");
        String name = scanner.next();

        System.out.println("Enter year: ");
        int year = 0;
        while(true){
            year = scanner.nextInt();
            if(year > 0 && year < 2020){
                break;
            } else {
                System.out.println("Incorrect input!");
            }
        }

        System.out.println("Enter fuel type (1 = Petroleum, 2 = Gazoline, 3 = Diezel, 4 = Electricity, 5 = Hybrid): ");
        FuelType fuelType = getFuelType(scanner);

        System.out.println("Enter price: ");
        double price = 0;
        while(true){
            price = scanner.nextDouble();
            if(price > 0){
                break;
            } else {
                System.out.println("Incorrect input!");
            }
        }

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

    private FuelType getFuelType(Scanner scanner) {
        int fuel_type = 0;
        while(true){
            fuel_type = scanner.nextInt();
            if(fuel_type > 0 && fuel_type < 6) {
                break;
            }
            else {
                System.out.println("Incorrect input!");
            }
        }

        FuelType fuelType = FuelType.Petroleum;

        switch (fuel_type){
            case 1: return FuelType.Petroleum;
            case 2: return FuelType.Gazoline;
            case 3: return FuelType.Diezel;
            case 4: return FuelType.Electricity;
            case 5: return FuelType.Hybrid;
        }
        return fuelType;
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
    private void showMotorcycles(){
        ArrayList<ProductListItem> products = ShopService.get_motorcycleShop().List();
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

    private void saleVehicleToCustomer() {
        String name, dob;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter tne name of the customer: ");
        name = scanner.nextLine();

        System.out.println("Enter the birth day  of the customer: ");
        dob = scanner.nextLine();

        Customer customer= new Customer(name, dob);

        int type = getVehicleType();
        int id = getEntityId();

        if(type == 1)
        {
            try {
                Car car = ShopService.get_carShop().Get(id);
                ShopService.get_carShop().Sale(car, customer);
            } catch (EntityNotFoundException e) {
                System.out.println("Product not found!");
            }
        }
        else if(type == 2)
        {
            try {
                Motorcycle moto = ShopService.get_motorcycleShop().Get(id);
                ShopService.get_motorcycleShop().Sale(moto, customer);
            } catch (EntityNotFoundException e) {
                System.out.println("Product not found!");
            }
        }
    }

    private void exit()
    {
        _isActive = false;
    }
}
