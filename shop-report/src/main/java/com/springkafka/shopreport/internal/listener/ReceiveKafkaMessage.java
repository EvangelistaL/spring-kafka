package com.springkafka.shopreport.internal.listener;

import com.springkafka.shopreport.api.model.ShopDTO;
import com.springkafka.shopreport.api.usecase.UpdateShopReport;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
@Slf4j
public class ReceiveKafkaMessage {

    private static final String SHOP_TOPIC_EVENT_NAME = "SHOP_TOPIC_EVENT";

    private UpdateShopReport updateShopReport;

    @KafkaListener(topics = SHOP_TOPIC_EVENT_NAME, groupId = "group_report")
    public void listenShopTopic(ShopDTO shopDTO){
        try {
            this.updateShopReport.execute(shopDTO);
        } catch (RuntimeException e){
            log.error("Erro no processamento da mensagem", e);
        }
    }
}
