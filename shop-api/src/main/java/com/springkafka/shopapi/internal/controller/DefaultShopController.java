package com.springkafka.shopapi.internal.controller;

import com.springkafka.shopapi.api.controller.ShopController;
import com.springkafka.shopapi.api.model.ShopDTO;
import com.springkafka.shopapi.api.usecase.CreatePurchase;
import com.springkafka.shopapi.api.usecase.RetrieveAllPurchaseList;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@ConditionalOnSingleCandidate(ShopController.class)
public class DefaultShopController implements ShopController{

    private CreatePurchase createPurchase;

    private RetrieveAllPurchaseList retrieveAllPurchaseList;

    @Override
    public List<ShopDTO> retrieveAllPurchaseList() {
        return this.retrieveAllPurchaseList.execute();
    }

    @Override
    public ShopDTO createPurchase(ShopDTO shopDTO) {
        return this.createPurchase.execute(shopDTO);
    }
}
