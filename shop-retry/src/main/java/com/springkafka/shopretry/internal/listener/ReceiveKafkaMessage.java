package com.springkafka.shopretry.internal.listener;

import com.springkafka.shopretry.api.model.ShopDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class ReceiveKafkaMessage {

    private KafkaTemplate<String, ShopDTO> kafkaTemplate;

    @KafkaListener(topics = "SHOP_TOPIC", groupId = "group_report")
    public void listenShopTopic(ShopDTO shopDTO){
        try {
            log.info("Compra recebida no tópico: {}.",
                    shopDTO.identifier());
            if (shopDTO.item() == null || shopDTO.item().isEmpty()){
                log.error("Compra sem itens");
                throw new RuntimeException();
            }
        } catch (RuntimeException e){
            log.info("Erro na aplicação");
            kafkaTemplate.send("SHOP_TOPIC_RETRY", shopDTO);
        }
    }

    @KafkaListener(topics = "SHOP_TOPIC_RETRY", groupId = "group_report")
    public void listenShopTopicRetry(ShopDTO shopDTO){
        log.info("Retentativa do processamento: {}",
                shopDTO.identifier());
    }
}
