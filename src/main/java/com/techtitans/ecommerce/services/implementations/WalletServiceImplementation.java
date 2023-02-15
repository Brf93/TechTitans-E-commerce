package com.techtitans.ecommerce.services.implementations;

import com.techtitans.ecommerce.models.Wallet;
import com.techtitans.ecommerce.repositories.WalletRepository;
import com.techtitans.ecommerce.services.WalletService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class WalletServiceImplementation implements WalletService {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    WalletRepository walletRepository;

    @Override
    public List<Wallet> getAllWallets() {
        entityManager.unwrap(Session.class).enableFilter("deletedWallet").setParameter("deleted", false);
        List<Wallet>  wallets = walletRepository.findAll();
        entityManager.unwrap(Session.class).disableFilter("deletedWallet");
        return  wallets;
    }

    @Override
    public Wallet getWalletById(Long id) {
        entityManager.unwrap(Session.class).enableFilter("deletedWallet").setParameter("deleted", false);
        Wallet wallet = walletRepository.findById(id).orElse(null);
        entityManager.unwrap(Session.class).disableFilter("deletedWallet");
        return  wallet;
    }

    @Override
    public Wallet getWalletByNumber(String number) {
        return walletRepository.findByNumber(number);
    }

    @Override
    public void saveWallet(Wallet wallet) {
        walletRepository.save(wallet);
    }

    @Override
    public void deleteWalletById(Long id) {
        walletRepository.deleteById(id);
    }
}
