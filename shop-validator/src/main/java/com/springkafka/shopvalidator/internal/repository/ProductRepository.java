package com.springkafka.shopvalidator.internal.repository;

import com.springkafka.shopvalidator.internal.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByProductIdentifier(String productIdentifier);
}
