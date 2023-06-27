package com.springkafka.shopapi.api.model;

import com.springkafka.shopapi.internal.entity.enums.PurchaseStatus;

import java.time.LocalDate;
import java.util.List;

public record ShopDTO(String identifier,
                      String buyerIdentifier,
                      PurchaseStatus status,
                      LocalDate dateShop,
                      List<ShopItemDTO> item) {
}
