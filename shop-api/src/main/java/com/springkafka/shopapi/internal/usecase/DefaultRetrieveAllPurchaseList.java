package com.springkafka.shopapi.internal.usecase;

import com.springkafka.shopapi.api.model.ShopDTO;
import com.springkafka.shopapi.api.usecase.RetrieveAllPurchaseList;
import com.springkafka.shopapi.internal.entity.Shop;
import com.springkafka.shopapi.internal.repository.ShopRepository;
import com.springkafka.shopapi.internal.utils.ShopConverterUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@AllArgsConstructor
@Service
public class DefaultRetrieveAllPurchaseList implements RetrieveAllPurchaseList {

    private ShopRepository shopRepository;

    @Override
    public List<ShopDTO> execute() {
        List<Shop> shopList = this.shopRepository.findAll();
        if (shopList.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "No purchases registered yet");
        }
        return shopList.stream()
                .map(ShopConverterUtils::convert)
                .toList();
    }
}
