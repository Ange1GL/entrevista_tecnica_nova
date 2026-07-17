package com.nova.customer_update.listener;

import com.nova.customer_update.dto.event.CreateCustomerEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomerEventListener {


    @KafkaListener(topics = "alta-customer", groupId = "${kafka.consumer.group-id}")
    public void consume(CreateCustomerEvent event) {
        log.info("SERVICIO DE UPDATE -> [KAFKA] Evento recibido: {}", event);
    }
}
