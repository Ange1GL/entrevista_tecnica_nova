package com.nova.customer_update.listener;

import com.nova.customer_update.dto.event.CreateCustomerEvent;
import com.nova.customer_update.service.CustomerValidationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomerEventListener {

    private final CustomerValidationService customerValidationService;

    @KafkaListener(topics = "alta-customer", groupId = "${kafka.consumer.group-id}")
    public void consume(CreateCustomerEvent event) {
        log.info("LISTENER -> Evento recibido: {}", event);
        customerValidationService.validate(event);
    }
}
