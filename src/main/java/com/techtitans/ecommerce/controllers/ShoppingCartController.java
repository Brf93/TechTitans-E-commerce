package com.techtitans.ecommerce.controllers;

import com.techtitans.ecommerce.dto.ShoppingCartDTO;
import com.techtitans.ecommerce.services.implementations.ShoppingCartServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ShoppingCartController {

    @Autowired
    ShoppingCartServiceImplementation shoppingCartService;

    @GetMapping("/shoppingCarts")
    public List<ShoppingCartDTO> getShoppingCarts(){
        return shoppingCartService.getAllShoppingCart().stream().map(shoppingCart -> new ShoppingCartDTO(shoppingCart)).collect(Collectors.toList());
    }

    @GetMapping("/shoppingCart/{id}")
    public ShoppingCartDTO getShoppingCartById(@PathVariable Long id){
        return new ShoppingCartDTO(shoppingCartService.findShoppingCart(id));
    }
}
