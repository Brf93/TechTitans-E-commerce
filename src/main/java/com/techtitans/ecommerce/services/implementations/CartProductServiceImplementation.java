package com.techtitans.ecommerce.services.implementations;

import com.techtitans.ecommerce.models.CartProduct;
import com.techtitans.ecommerce.repositories.CartProductRepository;
import com.techtitans.ecommerce.services.CartProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartProductServiceImplementation implements CartProductService {
    @Autowired
    CartProductRepository cartProductRepository;

    @Override
    public List<CartProduct> getAllCartProducts() {
        return cartProductRepository.findAll();
    }

    @Override
    public CartProduct findCartProductById(Long id) {
        return cartProductRepository.findById(id).orElse(null);
    }
}
