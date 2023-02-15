package com.techtitans.ecommerce.repositories;

import com.techtitans.ecommerce.models.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
}
