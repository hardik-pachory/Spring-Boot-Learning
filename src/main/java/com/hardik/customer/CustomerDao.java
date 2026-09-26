package com.hardik.customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDao {
    List<Customer> selectAllCustomers();
    Optional<Customer> selectCustomerById(Integer customerId);
    void insertCustomer(Customer customer);
    boolean existsPersonWithEmail(String email);
    void deleteCustomer(Integer customerId);
    boolean existsPersonWithId(Integer Id);
    void updateCustomer(Customer customer);
}
