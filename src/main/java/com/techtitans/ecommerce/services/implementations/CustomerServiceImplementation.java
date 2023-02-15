package com.techtitans.ecommerce.services.implementations;

import com.techtitans.ecommerce.models.Customer;
import com.techtitans.ecommerce.repositories.CartProductRepository;
import com.techtitans.ecommerce.repositories.CustomerRepository;
import com.techtitans.ecommerce.services.CustomerService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class CustomerServiceImplementation implements CustomerService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public List<Customer> getAllCustomers() {
        entityManager.unwrap(Session.class).enableFilter("deletedCustomer").setParameter("deleted", false);
        List<Customer> customers = customerRepository.findAll();
        entityManager.unwrap(Session.class).disableFilter("deletedCustomer");
        return customers;
    }

    @Override
    public Customer findCustomerById(Long id) {
        entityManager.unwrap(Session.class).enableFilter("deletedCustomer").setParameter("deleted", false);
        Customer customer = customerRepository.findById(id).orElse(null);
        entityManager.unwrap(Session.class).disableFilter("deletedCustomer");
        return customer;
    }

    @Override
    public Customer findCustomerByEmail(String email) {
        entityManager.unwrap(Session.class).enableFilter("deletedCustomer").setParameter("deleted", false);
        Customer customer = customerRepository.findByEmail(email);
        entityManager.unwrap(Session.class).disableFilter("deletedCustomer");
        return customer;
    }

    @Override
    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }
}
