package com.springkafka.shopvalidator.internal.usecase;

import com.springkafka.shopvalidator.api.model.ShopDTO;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class KafkaClient {

    private final KafkaTemplate<String, ShopDTO> kafkaTemplate;

    private static final String SHOP_TOPIC_EVENT_NAME = "SHOP_TOPIC_EVENT";

    public void sendMessage(ShopDTO msg){
        kafkaTemplate.send(SHOP_TOPIC_EVENT_NAME, msg);
    }
}
