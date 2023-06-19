package com.springkafka.shopapi.api.usecase;

import com.springkafka.shopapi.api.model.ShopDTO;

import java.util.List;

public interface RetrieveAllPurchaseList {
    List<ShopDTO> execute();
}
