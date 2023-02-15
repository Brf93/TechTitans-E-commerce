package com.techtitans.ecommerce.repositories;

import com.techtitans.ecommerce.models.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CartProductRepository extends JpaRepository<CartProduct, Long> {
}
