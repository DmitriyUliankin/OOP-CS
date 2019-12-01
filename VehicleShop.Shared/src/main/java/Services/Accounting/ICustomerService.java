package Services.Accounting;

import Data.Entities.Accounting.Customer;
import Exceptions.Entities.EntityAlreadyExistException;
import Exceptions.Entities.EntityNotFoundException;

public interface ICustomerService {
    Customer getCustomer(int id) throws EntityNotFoundException;
    void addCustomer(Customer customer) throws EntityAlreadyExistException;
}
