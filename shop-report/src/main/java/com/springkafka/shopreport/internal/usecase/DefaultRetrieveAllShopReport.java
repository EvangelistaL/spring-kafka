package com.springkafka.shopreport.internal.usecase;

import com.springkafka.shopreport.api.model.ShopReportDTO;
import com.springkafka.shopreport.api.usecase.RetrieveAllShopReport;
import com.springkafka.shopreport.internal.entity.ShopReport;
import com.springkafka.shopreport.internal.repository.ShopReportRepository;
import com.springkafka.shopreport.internal.utils.ShopReportConverterUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@AllArgsConstructor
@Service
public class DefaultRetrieveAllShopReport implements RetrieveAllShopReport {

    private ShopReportRepository shopReportRepository;

    @Override
    public List<ShopReportDTO> execute() {
        List<ShopReport> shopReports = this.shopReportRepository.findAll();
        if (shopReports.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        return shopReports.stream()
                .map(ShopReportConverterUtils::convert)
                .toList();
    }
}
