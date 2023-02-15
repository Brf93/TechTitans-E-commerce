package com.techtitans.ecommerce.repositories;

import com.techtitans.ecommerce.models.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface WalletRepository extends JpaRepository<Wallet, Long> {

    Wallet findByNumber(String number);
}
