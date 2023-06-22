package com.springkafka.shopreport.api.model;

import com.springkafka.shopreport.internal.entity.enums.PurchaseStatus;

public record ShopDTO(String identifier, PurchaseStatus status) {
}
