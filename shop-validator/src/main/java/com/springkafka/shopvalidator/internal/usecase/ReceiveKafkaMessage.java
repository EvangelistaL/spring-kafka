package com.springkafka.shopvalidator.internal.usecase;

import com.springkafka.shopvalidator.api.model.PurchaseStatus;
import com.springkafka.shopvalidator.api.model.ShopDTO;
import com.springkafka.shopvalidator.api.model.ShopItemDTO;
import com.springkafka.shopvalidator.internal.entity.Product;
import com.springkafka.shopvalidator.internal.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@AllArgsConstructor
@Service
@Slf4j
@Transactional
public class ReceiveKafkaMessage {

    private static final String SHOP_TOPIC_NAME = "SHOP_TOPIC";

    private ProductRepository productRepository;

    private KafkaClient kafkaClient;

    @KafkaListener(topics = SHOP_TOPIC_NAME, groupId = "group")
    public void listenShopTopic(ShopDTO shopDTO,
                                @Header(KafkaHeaders.RECEIVED_KEY) String key,
                                @Header(KafkaHeaders.RECEIVED_PARTITION) String partition,
                                @Header(KafkaHeaders.RECEIVED_TIMESTAMP) String timestamp){
        try{
            log.info("Compra recebida no tópico: {} com chave {} na partição {} hora {}",
                    shopDTO.identifier(), key, partition, timestamp);
            boolean success = true;
            for (ShopItemDTO itemDTO : shopDTO.item()){
                Product product = this.productRepository.findByProductIdentifier(itemDTO.productIdentifier())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                String.format("Product with identifier %s not found.", itemDTO.productIdentifier())
                        ));

                if (!isValidShop(itemDTO, product)){
                    shopError(shopDTO);
                    success = false;
                    break;
                }
            }
            if (success){
                shopSuccess(shopDTO);
            }
        } catch (RuntimeException e){
            log.error("Erro no processamento da compra {}",
                    shopDTO.identifier());
        }
    }

    private boolean isValidShop(ShopItemDTO shopItemDTO, Product product){
        return product != null && product.getAmount() >= shopItemDTO.amount();
    }

    private void shopError(ShopDTO shopDTO){
        log.info("Erro no processamento da compra {}.",
                shopDTO.identifier());
        kafkaClient.sendMessage(new ShopDTO(shopDTO.identifier(),
                shopDTO.buyerIdentifier(),
                PurchaseStatus.ERROR,
                shopDTO.dateShop(),
                shopDTO.item()));
    }

    private void shopSuccess(ShopDTO shopDTO){
        log.info("Compra {} efetuada com sucesso.",
                shopDTO.identifier());
        kafkaClient.sendMessage(new ShopDTO(shopDTO.identifier(),
                shopDTO.buyerIdentifier(),
                PurchaseStatus.SUCCESS,
                shopDTO.dateShop(),
                shopDTO.item()));
    }
}
