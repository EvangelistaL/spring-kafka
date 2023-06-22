package com.springkafka.shopreport.api.model;

import com.springkafka.shopreport.internal.entity.enums.PurchaseStatus;

public record ShopReportDTO(PurchaseStatus purchaseStatus, Integer amount) {
}
