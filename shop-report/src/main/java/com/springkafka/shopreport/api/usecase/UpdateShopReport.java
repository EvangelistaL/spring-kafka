package com.springkafka.shopreport.api.usecase;

import com.springkafka.shopreport.api.model.ShopDTO;

public interface UpdateShopReport {

    void execute(ShopDTO shopDTO);
}
