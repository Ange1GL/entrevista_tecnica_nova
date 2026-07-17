package com.nova.customer_registration.listener;

import com.nova.customer_registration.dto.event.CustomerValidatedEvent;
import com.nova.customer_registration.service.UpdateCustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomerValidatedEventListener {

    private final UpdateCustomerService updateCustomerService;

    @KafkaListener(topics = "customer-validated", groupId = "${kafka.consumer.group-id}")
    public void consume(CustomerValidatedEvent event) {
        log.info("Evento de validacion recibido: {}", event);
        updateCustomerService.updateStatus(event);
    }
}
