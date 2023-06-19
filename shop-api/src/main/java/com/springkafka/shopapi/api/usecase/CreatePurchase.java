package com.springkafka.shopapi.api.usecase;

import com.springkafka.shopapi.api.model.ShopDTO;

public interface CreatePurchase {
    ShopDTO execute(ShopDTO shopDTO);
}
