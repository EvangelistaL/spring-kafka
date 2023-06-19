package com.springkafka.shopapi.internal.utils;

import com.springkafka.shopapi.api.model.ShopItemDTO;
import com.springkafka.shopapi.internal.entity.ShopItem;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ShopItemConverterUtils {

    public static ShopItem convert(ShopItemDTO shopItemDTO){
        ShopItem shopItem =	new	ShopItem();
        shopItem.setProductIdentifier(shopItemDTO.productIdentifier());
        shopItem.setAmount(shopItemDTO.amount());
        shopItem.setPrice(shopItemDTO.price());
        return shopItem;
    }

    public static ShopItemDTO convert(ShopItem shopItem){
        return new ShopItemDTO(shopItem.getProductIdentifier(),
                shopItem.getAmount(),
                shopItem.getPrice());
    }
}
