import Data.Entities.Accounting.Customer;
import Data.Entities.Accounting.Transaction;
import Data.Entities.Vehicle.Car;
import Data.Entities.Vehicle.Motorcycle;
import Data.Entities.Vehicle.Vehicle;
import Exceptions.Entities.EntityAlreadyExistException;
import Exceptions.Entities.EntityNotFoundException;
import Exceptions.Entities.ProductAlreadySoldException;
import Repositories.CarCsvRepository;
import Repositories.CustomerCsvRepository;
import Repositories.MotorcycleCsvRepository;
import Repositories.TransactionCsvRepository;
import Services.Accounting.AccountingService;
import Services.Accounting.CustomerService;
import Services.Accounting.IAccountingService;
import Services.Accounting.ICustomerService;
import Services.Shop.Models.ProductListItem;
import Services.Shop.Vehicle.CarShop;
import Services.Shop.Vehicle.MotorcycleShop;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ShopService
{
    private static final String CarCsvRepositoryPath = "carRepository.csv";
    private static final String MotorcycleCsvRepositoryPath = "motorcycleRepository.csv";
    private static final String TransactionCsvRepositoryPath = "transactionRepository.csv";
    private static final String CustomerCsvRepositoryPath = "customerRepository.csv";

    private static AccountingService _accountingService;
    private static TransactionCsvRepository _transactionRepository;
    private static ICustomerService _customerService;
    private static CustomerCsvRepository _customerRepository;
    private static CarShop _carShop;
    private static CarCsvRepository _carRepository;
    private static MotorcycleShop _motorcycleShop;
    private static MotorcycleCsvRepository _motorcycleRepository;

    public static ICustomerService get_customerService(){
        if(_customerService == null)
        {
            _customerService = new CustomerService(get_customerRepository());
        }
        return _customerService;
    }

    public static CarShop get_carShop() {
        if(_carShop == null)
        {
            _carShop = new CarShop(get_accountingService(), get_carRepository());
        }
        return _carShop;
    }

    public static MotorcycleShop get_motorcycleShop() {
        if(_motorcycleShop == null)
        {
            _motorcycleShop = new MotorcycleShop(get_accountingService(), get_motorcycleRepository());
        }
        return _motorcycleShop;
    }

    public static ArrayList<ProductListItem> listAllVehicles() {
        List<ProductListItem> cars = get_carShop().List();
        List<ProductListItem> motorcycles = get_motorcycleShop().List();

        ArrayList<ProductListItem> products = new ArrayList<>();
        products.addAll(cars);
        products.addAll(motorcycles);

        products.sort((i1, i2) -> i2.get_name().compareTo(i1.get_name()));

        return products;
    }

    public static List<Transaction> listTransactions(LocalDate date)
    {
        if(get_transactionRepository().List() == null || get_transactionRepository().List().isEmpty())
            return null;

        return get_transactionRepository().List()
                .stream()
                .filter(x -> x.get_date().getDayOfYear() == date.getDayOfYear())
                .collect(Collectors.toList());
    }

    public static void Add(Vehicle v) throws EntityAlreadyExistException {
        if(v instanceof  Car)
        {
            get_carShop().AddToStock((Car)v);
        }
        else if(v instanceof Motorcycle)
        {
            get_motorcycleShop().AddToStock((Motorcycle) v);
        }
    }

    public static void Sale(int id, Class type, Customer customer) throws EntityNotFoundException, ProductAlreadySoldException {
        if (type == Car.class) {
            get_carShop().Sale(id, customer);
        }
        else if (type == Motorcycle.class) {
            get_motorcycleShop().Sale(id, customer);
        }
    }

    private static CarCsvRepository get_carRepository()
    {
        if(_carRepository == null)
        {
            _carRepository = new CarCsvRepository(CarCsvRepositoryPath);
        }
        return _carRepository;
    }

    private static IAccountingService get_accountingService()
    {
        if(_accountingService == null)
        {
            _accountingService = new AccountingService(get_transactionRepository());
        }
        return  _accountingService;
    }

    private static TransactionCsvRepository get_transactionRepository()
    {
        if(_transactionRepository == null)
        {
            _transactionRepository = new TransactionCsvRepository(TransactionCsvRepositoryPath);
        }
        return _transactionRepository;
    }

    private static MotorcycleCsvRepository get_motorcycleRepository() {
        if(_motorcycleRepository == null)
        {
            _motorcycleRepository = new MotorcycleCsvRepository(MotorcycleCsvRepositoryPath);
        }
        return _motorcycleRepository;
    }

    private static CustomerCsvRepository get_customerRepository()
    {
        if(_customerRepository == null)
        {
            _customerRepository = new CustomerCsvRepository(CustomerCsvRepositoryPath);
        }
        return _customerRepository;
    }
}
