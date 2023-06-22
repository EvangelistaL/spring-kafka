package com.springkafka.shopreport.internal.utils;

import com.springkafka.shopreport.api.model.ShopReportDTO;
import com.springkafka.shopreport.internal.entity.ShopReport;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ShopReportConverterUtils {

    public static ShopReportDTO convert(ShopReport shopReport){
        return new ShopReportDTO(shopReport.getPurchaseStatus(), shopReport.getAmount());
    }
}
