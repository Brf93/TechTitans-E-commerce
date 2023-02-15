package com.techtitans.ecommerce.controllers;

import com.techtitans.ecommerce.dto.WalletDTO;
import com.techtitans.ecommerce.services.implementations.WalletServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class WalletController {

    @Autowired
    WalletServiceImplementation walletService;

    @GetMapping("/wallets")
    public List<WalletDTO> getWallets(){
        return walletService.getAllWallets().stream().map(wallet -> new WalletDTO(wallet)).collect(Collectors.toList());
    }

    @GetMapping("/wallet/{id}")
    public WalletDTO getWalletById(@PathVariable Long id){
        return new WalletDTO(walletService.getWalletById(id));
    }
}
