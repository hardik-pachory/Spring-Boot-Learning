package com.hardik.customer;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("list")
public class CustomerListDataAccessService implements CustomerDao{

    // Fake DB
    private static List<Customer> customers;

    static{
        customers = new ArrayList<>();

        Customer alex = new Customer(1,"Alex","alex@mail.com",21);
        customers.add(alex);

        Customer rubina = new Customer(2, "Rubina","rubina@mail.com",19);
        customers.add(rubina);
    }

    @Override
    public List<Customer> selectAllCustomers() {
        return customers;
    }

    @Override
    public Optional<Customer> selectCustomerById(Integer customerId) {
        return customers.stream()
                .filter(c -> c.getId().equals(customerId))
                .findFirst();
    }

    @Override
    public void insertCustomer(Customer customer) {
        customers.add(customer);
    }

    @Override
    public boolean existsPersonWithEmail(String email) {
        return customers.stream()
                .anyMatch(c->c.getEmail().equals(email));
    }

    @Override
    public void deleteCustomer(Integer customerId) {

    }

    @Override
    public boolean existsPersonWithId(Integer Id) {
        return false;
    }
}
