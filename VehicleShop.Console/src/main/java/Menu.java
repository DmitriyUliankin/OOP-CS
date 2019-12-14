import Data.Entities.Accounting.Customer;
import Data.Entities.Accounting.Transaction;
import Data.Entities.Vehicle.Car;
import Data.Entities.Vehicle.Enums.FuelType;
import Data.Entities.Vehicle.Motorcycle;
import Data.Entities.Vehicle.Vehicle;
import Exceptions.Entities.EntityAlreadyExistException;
import Exceptions.Entities.EntityNotFoundException;
import Exceptions.Entities.ProductAlreadySoldException;
import Services.Shop.Models.ProductListItem;
import Servises.DateInputValidator;
import Servises.DoubleInputValidator;
import Servises.IntInputValidator;
import de.vandermeer.asciitable.AsciiTable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private IntInputValidator _intValidator = new IntInputValidator();
    private DoubleInputValidator _doubleValidator = new DoubleInputValidator();
    private DateInputValidator _dateValidator = new DateInputValidator();
    private boolean _isActive = true;

    public boolean getStatus() {
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
        System.out.println("8. Print sales by date");
        System.out.println("9. Exit");
        awaitUserInput();
    }

    private void awaitUserInput() {
        int input = _intValidator.getInput();
        switch (input) {
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
                printTransactions();
                break;
            }
            case 9: {
                exit();
                break;
            }
            default: {
                System.out.println("Incorrect input!");
            }
        }
    }

    private int getVehicleType() {
        System.out.println("Choose: \"1\" - Car or \"2\" - Motorcycle");
        int vehicleType = 0;
        while (true) {
            vehicleType = _intValidator.getInput();
            if (vehicleType > 0 && vehicleType < 3) {
                break;
            } else {
                System.out.println("Incorrect input!");
            }
        }
        return vehicleType;
    }

    private int getEntityId() {
        System.out.println("Enter ID:");
        return _intValidator.getInput();
    }

    private void addVehicle() {
        int vehicleType = getVehicleType();

        System.out.println("Enter serial number: ");
        int serialNumber = _intValidator.getInput();

        System.out.println("Enter name: ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();

        System.out.println("Enter year: ");
        int year = _intValidator.getInput();

        System.out.println("Enter fuel type (1 = Petroleum, 2 = Gazoline, 3 = Diezel, 4 = Electricity, 5 = Hybrid): ");
        FuelType fuelType = getFuelType();

        System.out.println("Enter price: ");
        double price = _doubleValidator.getInput();

        try {
            ShopService.Add(vehicleType == 1
                    ? new Car(serialNumber, name, year, fuelType, price)
                    : new Motorcycle(serialNumber, name, year, fuelType, price));
        } catch (EntityAlreadyExistException e) {
            System.out.println(e.getMessage());
        }
    }

    private FuelType getFuelType() {
        int fuel_type = 0;
        while (true) {
            fuel_type = _intValidator.getInput();
            if (fuel_type > 0 && fuel_type < 6) {
                break;
            } else {
                System.out.println("Incorrect input!");
            }
        }

        switch (fuel_type) {
            case 1:
                return FuelType.Petroleum;
            case 2:
                return FuelType.Gasoline;
            case 3:
                return FuelType.Diesel;
            case 4:
                return FuelType.Electricity;
            default:
                return FuelType.Hybrid;
        }
    }

    private void deleteVehicle() {
        int type = getVehicleType();

        ArrayList<ProductListItem> products = type == 1 ? ShopService.get_carShop().List() : ShopService.get_motorcycleShop().List();
        printProductList(products);
        if(products == null || products.isEmpty())
            return;

        int id = getEntityId();
        try {
            if (type == 1) {
                ShopService.get_carShop().RemoveFromStock(id);
            }
            else if(type == 2){
                ShopService.get_motorcycleShop().RemoveFromStock(id);
            }
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void showAllVehicles() {
        ArrayList<ProductListItem> products = ShopService.listAllVehicles();
        printProductList(products);
    }

    private void showCars() {
        ArrayList<ProductListItem> products = ShopService.get_carShop().List();
        printProductList(products);
    }

    private void showMotorcycles() {
        ArrayList<ProductListItem> products = ShopService.get_motorcycleShop().List();
        printProductList(products);
    }

    private void printProductList(ArrayList<ProductListItem> products) {
        if(products == null || products.isEmpty())
        {
            System.out.println("The stock is empty for now.");
            return;
        }
        AsciiTable table = new AsciiTable();
        table.addRule();
        table.addRow("ID","Type","Name","Price");
        for (ProductListItem p : products) {
            table.addRule();
            table.addRow(p.get_id(), p.get_type(), p.get_name(), p.get_price());
        }
        table.addRule();
        String result = table.render();
        System.out.println(result);
    }

    private void getVehicleDetails() {
        int type = getVehicleType();

        ArrayList<ProductListItem> products = type == 1 ? ShopService.get_carShop().List() : ShopService.get_motorcycleShop().List();
        printProductList(products);
        if(products == null || products.isEmpty())
            return;

        int id = getEntityId();
        try {
            Vehicle vehicle = type == 1
                    ? ShopService.get_carShop().Get(id)
                    : type == 2
                    ? ShopService.get_motorcycleShop().Get(id)
                    : null;

            printVehicle(vehicle);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void printVehicle(Vehicle v) {
        AsciiTable table = new AsciiTable();
        table.addRule();
        table.addRow("Type", v.get_productType());
        table.addRule();
        table.addRow("Serial number", v.get_serialNumber());
        table.addRule();
        table.addRow("Name", v.get_name());
        table.addRule();
        table.addRow("Year", v.get_year());
        table.addRule();
        table.addRow("Fuel type", v.get_fuelType().toString());
        table.addRule();
        table.addRow("Status", v.get_status().toString());
        table.addRule();
        table.addRow("Price", v.get_price());
        table.addRule();
        String result = table.render();
        System.out.println(result);
    }

    private void printTransactions() {
        LocalDate date = getDate();
        List<Transaction> sales = ShopService.listTransactions(date);

        if(sales == null || sales.isEmpty())
            System.out.println("No transactions was found!");
        else {
            AsciiTable header = new AsciiTable();
            header.addRule();
            header.addRow("Transaction by "+ date);
            header.addRule();

            AsciiTable table = new AsciiTable();
            for (Transaction t : sales) {
                table.addRule();
                table.addRow("Transaction ID", t.get_key());
                table.addRule();
                table.addRow("Transaction type", t.get_transactionType().toString());
                table.addRule();
                table.addRow("Product type", t.get_productType());
                table.addRule();
                table.addRow("Product id", t.get_productId());
                table.addRule();
                table.addRow("Product name", t.get_productName());
                table.addRule();
                table.addRow("Price", t.get_salePrice());
                table.addRule();
                table.addRow("Customer", t.get_customerFIO());
                table.addRule();
                table.addRow("Date", t.get_date().toString());
                table.addRule();
            }
            String headResult= header.render();
            System.out.println(headResult);

            String transactionResult = table.render();
            System.out.println(transactionResult);
        }
    }

    private LocalDate getDate() {
        System.out.println("Print transactions by current day? [1 = yes, 2 = choose another date]");
        int input = _intValidator.getInput();
        if(input == 2)
        {
            return _dateValidator.getInput();
        }
        else {
            return LocalDate.now();
        }
    }

    private void saleVehicleToCustomer() {
        int type = getVehicleType();

        ArrayList<ProductListItem> products = type == 1 ? ShopService.get_carShop().List() : ShopService.get_motorcycleShop().List();
        printProductList(products);
        if(products == null || products.isEmpty())
            return;

        int id = getEntityId();
        if(products.stream().noneMatch(x -> x.get_id() == id))
        {
            System.out.println("Vehicle \'"+id+"\' not found!");
            return;
        }

        System.out.println("Enter customer ID:");
        int customerId = _intValidator.getInput();
        Customer customer;
        try {
            customer = ShopService.get_customerService().getCustomer(customerId);
        } catch (EntityNotFoundException e) {
            customer = createNewCustomer(customerId);
        }

        try {
            ShopService.Sale(id, type == 1 ? Car.class : Motorcycle.class, customer);
        } catch (EntityNotFoundException | ProductAlreadySoldException e) {
            System.out.println(e.getMessage());
        }
    }

    private Customer createNewCustomer(int id) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter customer name: ");
        String name = scanner.nextLine();

        System.out.println("Enter customer's birth date: ");
        String dob = scanner.nextLine();

        try {
            ShopService.get_customerService().addCustomer(new Customer(id, name, dob));
        } catch (EntityAlreadyExistException e) { }

        Customer customer = null;
        try {
            customer = ShopService.get_customerService().getCustomer(id);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return customer;
    }

    private void exit() {
        _isActive = false;
    }
}
