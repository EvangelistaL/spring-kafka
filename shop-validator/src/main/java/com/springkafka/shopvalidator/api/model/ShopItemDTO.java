package com.springkafka.shopvalidator.api.model;

import java.math.BigDecimal;

public record ShopItemDTO(String productIdentifier, Integer amount, BigDecimal price) {
}
