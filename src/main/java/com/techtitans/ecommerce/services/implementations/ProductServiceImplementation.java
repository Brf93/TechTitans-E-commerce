package com.techtitans.ecommerce.services.implementations;

import com.techtitans.ecommerce.models.Product;
import com.techtitans.ecommerce.repositories.ProductRepository;
import com.techtitans.ecommerce.services.ProductService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class ProductServiceImplementation implements ProductService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        entityManager.unwrap(Session.class).enableFilter("deletedProduct").setParameter("deleted", false);
        List<Product> products = productRepository.findAll();
        entityManager.unwrap(Session.class).disableFilter("deletedProduct");
        return products;
    }

    @Override
    public Product findProductById(Long id) {
        entityManager.unwrap(Session.class).enableFilter("deletedProduct").setParameter("deleted", false);
        Product product = productRepository.findById(id).orElse(null);
        entityManager.unwrap(Session.class).disableFilter("deletedProduct");
        return product;
    }

    @Override
    public void saveProduct(Product product){
        productRepository.save(product);
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }
}
