package com.springkafka.shopapi.internal.usecase;

import com.springkafka.shopapi.api.model.ShopDTO;
import com.springkafka.shopapi.internal.entity.Shop;
import com.springkafka.shopapi.internal.repository.ShopRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@AllArgsConstructor
@Service
@Slf4j
public class ReceiveKafkaMessage {

    private ShopRepository shopRepository;

    private static final String SHOP_TOPIC_EVENT_NAME = "SHOP_TOPIC_EVENT";

    @KafkaListener(topics = SHOP_TOPIC_EVENT_NAME, groupId = "group")
    public void listenShopEvents(ShopDTO shopDTO){
        try{
            log.info("Status da compra recebida no tópico: {}.",
                    shopDTO.identifier());
            Shop shop = this.shopRepository.findByIdentifier(shopDTO.identifier())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            String.format("Purchase with identifier %s not found", shopDTO.identifier())
                            ));
            shop.setStatus(shopDTO.status());
            this.shopRepository.save(shop);
        } catch (RuntimeException e){
            log.error("Erro no processamento da compra {}",
                    shopDTO.identifier());
        }
    }
}
