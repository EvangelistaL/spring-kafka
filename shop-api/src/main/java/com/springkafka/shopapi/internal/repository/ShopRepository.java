package com.springkafka.shopapi.internal.repository;

import com.springkafka.shopapi.internal.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {

    Optional<Shop> findByIdentifier(String identifier);
}
