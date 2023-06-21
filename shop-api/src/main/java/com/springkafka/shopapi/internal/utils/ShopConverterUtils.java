package com.springkafka.shopapi.internal.utils;

import com.springkafka.shopapi.api.model.ShopDTO;
import com.springkafka.shopapi.internal.entity.Shop;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ShopConverterUtils {

    public static Shop convert(ShopDTO shopDTO){
        Shop shop = new Shop();
        shop.setIdentifier(shopDTO.identifier());
        shop.setStatus(shopDTO.status());
        shop.setDateShop(shopDTO.dateShop());
        shop.setShopItems(shopDTO.item()
                .stream()
                .map(ShopItemConverterUtils::convert)
                .toList());
        return shop;
    }

    public static ShopDTO convert(Shop shop){
        return new ShopDTO(shop.getIdentifier(),
                shop.getStatus(),
                shop.getDateShop(),
                shop.getShopItems().stream()
                        .map(ShopItemConverterUtils::convert)
                        .toList());
    }
}
