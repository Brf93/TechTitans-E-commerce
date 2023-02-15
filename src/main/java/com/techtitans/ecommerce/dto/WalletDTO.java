package com.techtitans.ecommerce.dto;

import com.techtitans.ecommerce.models.Wallet;

import java.util.List;

import java.util.stream.Collectors;

public class WalletDTO {
    private String number;

    private Double balance;

    private List<ShoppingCartDTO> shoppingCarts;


    public WalletDTO(Wallet wallet) {
        this.number = wallet.getNumber();
        this.balance = wallet.getBalance();
        this.shoppingCarts = wallet.getShoppingCarts().stream().map(shoppingCart -> new ShoppingCartDTO(shoppingCart)).collect(Collectors.toList());
    }

    public String getNumber() {
        return number;
    }

    public Double getBalance() {
        return balance;
    }

    public List<ShoppingCartDTO> getShoppingCarts() {
        return shoppingCarts;
    }
}
