package com.springkafka.shopreport.api.usecase;

import com.springkafka.shopreport.api.model.ShopReportDTO;

import java.util.List;

public interface RetrieveAllShopReport {

    List<ShopReportDTO> execute();
}
