package com.techtitans.ecommerce.services;

import com.techtitans.ecommerce.models.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {

    List<ShoppingCart> getAllShoppingCart();

    ShoppingCart findShoppingCart(Long id);
}
