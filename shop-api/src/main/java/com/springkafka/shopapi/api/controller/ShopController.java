package com.springkafka.shopapi.api.controller;

import com.springkafka.shopapi.api.model.ShopDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shop")
public interface ShopController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<ShopDTO> retrieveAllPurchaseList();

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ShopDTO createPurchase(@RequestBody ShopDTO shopDTO);
}
