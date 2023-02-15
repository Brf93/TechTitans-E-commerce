package com.techtitans.ecommerce.services.implementations;

import com.techtitans.ecommerce.models.ShoppingCart;
import com.techtitans.ecommerce.repositories.ShoppingCartRepository;
import com.techtitans.ecommerce.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartServiceImplementation implements ShoppingCartService {

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Override
    public List<ShoppingCart> getAllShoppingCart() {
        return shoppingCartRepository.findAll();
    }

    @Override
    public ShoppingCart findShoppingCart(Long id) {
        return shoppingCartRepository.findById(id).orElse(null);
    }
}
