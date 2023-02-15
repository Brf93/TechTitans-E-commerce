package com.techtitans.ecommerce.services;

import com.techtitans.ecommerce.models.CartProduct;

import java.util.List;

public interface CartProductService {

    List<CartProduct> getAllCartProducts();

    CartProduct findCartProductById(Long id);
}
