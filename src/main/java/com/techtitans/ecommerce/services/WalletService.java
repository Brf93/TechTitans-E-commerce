package com.techtitans.ecommerce.services;

import com.techtitans.ecommerce.models.Wallet;

import java.util.List;

public interface WalletService {

    List<Wallet> getAllWallets();

    Wallet getWalletById(Long id);

    Wallet getWalletByNumber(String number);

    void saveWallet(Wallet wallet);

    void deleteWalletById(Long id);
}
