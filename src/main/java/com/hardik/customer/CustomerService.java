package com.hardik.customer;

import com.hardik.exception.DuplicateResourceException;
import com.hardik.exception.RequestValidationException;
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
        if(!customerDao.existsPersonWithId(customerId)){
            throw new ResourceNotFound("Customer with Id [%s] not found.".formatted(customerId));
        }
        customerDao.deleteCustomer(customerId);
    }

    public void updateCustomer(Integer customerId, CustomerUpdateRequest updateRequest){
        Customer customer = getCustomer(customerId);
        boolean changes = false;
        if(updateRequest.name()!=null && !customer.getName().equals(updateRequest.name())){
            customer.setName(updateRequest.name());
            changes = true;
        }

        if(updateRequest.email()!=null && !customer.getEmail().equals(updateRequest.email())){
            if(customerDao.existsPersonWithEmail(updateRequest.email())){
                throw new DuplicateResourceException("Email already in use.");
            }
            customer.setEmail(updateRequest.email());
            changes=true;
        }

        if(updateRequest.age()!=null && !customer.getAge().equals(updateRequest.age())){
            customer.setAge(Integer.valueOf(updateRequest.age()));
            changes=true;
        }

        if(!changes){
            throw new RequestValidationException("No data change captured.");
        }
        customerDao.updateCustomer(customer);
    }
}
