package com.hardik.customer;

import com.hardik.exception.DuplicateResourceException;
import com.hardik.exception.ResourceNotFound;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerDao customerDao;

//    So we have name the Dao Implementation - jpa and list. So whatever implementation we want to use, we can just
//    mention the name in the qualifier.
    public CustomerService(@Qualifier("jpa") CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public List<Customer> getAllCustomers(){
        return customerDao.selectAllCustomers();
    }

    public Customer getCustomer(Integer id){
        return customerDao.selectCustomerById(id)
                .orElseThrow(
                        ()-> new ResourceNotFound("Customer with Id [%s] not found.".formatted(id))
                );
    }

    public void addCustomer(CustomerRegistrationRequest customerRegistrationRequest){
        if (customerDao.existsPersonWithEmail(customerRegistrationRequest.email())){
            throw new DuplicateResourceException(
                    "Customer with Email [%s] already exists.".formatted(customerRegistrationRequest.email())
            );
        }
        Customer customer = new Customer(
                customerRegistrationRequest.name(),
                customerRegistrationRequest.email(),
                customerRegistrationRequest.age()
        );
        customerDao.insertCustomer(customer);
    }

    public void deleteCustomer(Integer customerId){
        customerDao.deleteCustomer(customerId);
    }
}
