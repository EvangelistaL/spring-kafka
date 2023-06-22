package com.springkafka.shopreport.internal.controller;

import com.springkafka.shopreport.api.controller.ShopReportController;
import com.springkafka.shopreport.api.model.ShopReportDTO;
import com.springkafka.shopreport.api.usecase.RetrieveAllShopReport;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@ConditionalOnSingleCandidate(ShopReportController.class)
@RestController
public class DefaultShopReportController implements ShopReportController{

    private RetrieveAllShopReport retrieveAllShopReport;

    @Override
    public List<ShopReportDTO> retrieveAllShopReport() {
        return this.retrieveAllShopReport.execute();
    }
}
