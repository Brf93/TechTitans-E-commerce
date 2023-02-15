package com.techtitans.ecommerce.controllers;

import com.techtitans.ecommerce.dto.CartProductDTO;
import com.techtitans.ecommerce.services.implementations.CartProductServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CartProductController {
    @Autowired
    CartProductServiceImplementation cartProductService;

    @GetMapping("/cartProducts")
    public List<CartProductDTO> getCartProducts(){
        return cartProductService.getAllCartProducts().stream().map(cartProduct -> new CartProductDTO(cartProduct)).collect(Collectors.toList());
    }

    @GetMapping("/cartProduct/{id}")
    public CartProductDTO getCartProductById(@PathVariable Long id){
        return new CartProductDTO(cartProductService.findCartProductById(id));
    }
}
