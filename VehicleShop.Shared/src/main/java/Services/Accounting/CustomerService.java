package Services.Accounting;

import Data.Entities.Accounting.Customer;
import Data.Repository.IEntityRepository;
import Exceptions.Entities.EntityAlreadyExistException;
import Exceptions.Entities.EntityNotFoundException;

public class CustomerService implements ICustomerService{

    public CustomerService(IEntityRepository<Integer, Customer> customerRepository)
    {
        _customerRepository = customerRepository;
    }

    private IEntityRepository<Integer, Customer> _customerRepository;

    @Override
    public Customer getCustomer(int id) throws EntityNotFoundException {
        return _customerRepository.Get(id);
    }

    @Override
    public void addCustomer(Customer customer) throws EntityAlreadyExistException {
        Customer existentCst = null;
        try{
            existentCst = _customerRepository.Get(customer.get_key());
        } catch (Exception ex){}

        if(existentCst == null) _customerRepository.Create(customer);
    }
}
