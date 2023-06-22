package com.springkafka.shopreport.internal.usecase;

import com.springkafka.shopreport.api.model.ShopDTO;
import com.springkafka.shopreport.api.usecase.UpdateShopReport;
import com.springkafka.shopreport.internal.entity.ShopReport;
import com.springkafka.shopreport.internal.repository.ShopReportRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@AllArgsConstructor
@Service
@Slf4j
@Transactional
public class DefaultUpdateShopReport implements UpdateShopReport {

    private ShopReportRepository shopReportRepository;

    @Override
    public void execute(ShopDTO shopDTO) {
        ShopReport shopReport = this.shopReportRepository.findByPurchaseStatus(shopDTO.status())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Invalid purchase status = %s", shopDTO.status().name())
                ));
        shopReport.setAmount(shopReport.getAmount() + 1);
        this.shopReportRepository.save(shopReport);
    }
}
