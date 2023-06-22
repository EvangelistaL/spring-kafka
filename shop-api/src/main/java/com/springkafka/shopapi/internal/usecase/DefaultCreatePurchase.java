package com.springkafka.shopapi.internal.usecase;

import com.springkafka.shopapi.api.model.ShopDTO;
import com.springkafka.shopapi.api.usecase.CreatePurchase;
import com.springkafka.shopapi.internal.entity.Shop;
import com.springkafka.shopapi.internal.entity.ShopItem;
import com.springkafka.shopapi.internal.entity.enums.PurchaseStatus;
import com.springkafka.shopapi.internal.repository.ShopRepository;
import com.springkafka.shopapi.internal.utils.ShopConverterUtils;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@Service
@Transactional
public class DefaultCreatePurchase implements CreatePurchase {

    private ShopRepository shopRepository;

    private KafkaClient kafkaClient;

    @Override
    public ShopDTO execute(ShopDTO shopDTO) {
        Shop shopToSave = ShopConverterUtils.convert(shopDTO);
        shopToSave.setIdentifier(UUID.randomUUID().toString());
        shopToSave.setStatus(PurchaseStatus.PENDING);
        shopToSave.setDateShop(LocalDate.now());

        for (ShopItem shopItem : shopToSave.getShopItems()){
            shopItem.setShop(shopToSave);
        }

        ShopDTO shopSaved = ShopConverterUtils.convert(this.shopRepository.save(shopToSave));
        kafkaClient.sendMessage(shopSaved);
        return shopSaved;
    }
}
