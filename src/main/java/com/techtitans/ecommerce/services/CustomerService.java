package com.techtitans.ecommerce.services;

import com.techtitans.ecommerce.models.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> getAllCustomers();

    Customer findCustomerById(Long id);

    Customer findCustomerByEmail(String email);

    void saveCustomer(Customer customer);

    void deleteCustomerById(Long id);
}
