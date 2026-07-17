package com.nova.customer_registration.listener;

import com.nova.customer_registration.dto.event.CreateCustomerEvent;
import com.nova.customer_registration.service.CustomerValidationService;
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
