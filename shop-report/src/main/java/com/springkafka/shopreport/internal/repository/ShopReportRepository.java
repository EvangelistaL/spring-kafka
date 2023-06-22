package com.springkafka.shopreport.internal.repository;

import com.springkafka.shopreport.internal.entity.enums.PurchaseStatus;
import com.springkafka.shopreport.internal.entity.ShopReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShopReportRepository extends JpaRepository<ShopReport, Long> {

    Optional<ShopReport> findByPurchaseStatus(PurchaseStatus purchaseStatus);
}
