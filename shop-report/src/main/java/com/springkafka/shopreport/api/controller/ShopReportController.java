package com.springkafka.shopreport.api.controller;

import com.springkafka.shopreport.api.model.ShopReportDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/shop-report")
public interface ShopReportController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<ShopReportDTO> retrieveAllShopReport();
}
