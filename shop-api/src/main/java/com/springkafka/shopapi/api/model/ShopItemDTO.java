package com.springkafka.shopapi.api.model;

import java.math.BigDecimal;

public record ShopItemDTO(String productIdentifier, Integer amount, BigDecimal price) {
}
