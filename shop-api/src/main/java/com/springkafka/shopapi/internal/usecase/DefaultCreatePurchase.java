package com.springkafka.shopapi.internal.usecase;

import com.springkafka.shopapi.api.model.ShopDTO;
import com.springkafka.shopapi.api.usecase.CreatePurchase;
import com.springkafka.shopapi.internal.entity.Shop;
import com.springkafka.shopapi.internal.entity.enums.PurchaseStatus;
import com.springkafka.shopapi.internal.repository.ShopRepository;
import com.springkafka.shopapi.internal.utils.ShopConverterUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@Service
public class DefaultCreatePurchase implements CreatePurchase {

    private ShopRepository shopRepository;

    @Override
    public ShopDTO execute(ShopDTO shopDTO) {
        Shop shopToSave = ShopConverterUtils.convert(shopDTO);
        shopToSave.setIdentifier(UUID.randomUUID().toString());
        shopToSave.setStatus(PurchaseStatus.PENDING);
        shopToSave.setDateShop(LocalDate.now());
        Shop shopCreated = this.shopRepository.save(shopToSave);
        return ShopConverterUtils.convert(shopCreated);
    }
}
