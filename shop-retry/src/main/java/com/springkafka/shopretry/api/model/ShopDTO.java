package com.springkafka.shopretry.api.model;

import java.time.LocalDate;
import java.util.List;

public record ShopDTO(String identifier,
                      String buyerIdentifier,
                      PurchaseStatus status,
                      LocalDate dateShop,
                      List<ShopItemDTO> item) {
}
