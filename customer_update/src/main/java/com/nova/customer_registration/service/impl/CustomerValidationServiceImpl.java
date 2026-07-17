package com.nova.customer_registration.service.impl;

import com.nova.customer_registration.constant.StatusCustomerEnum;
import com.nova.customer_registration.constant.TopicCustomerEnum;
import com.nova.customer_registration.dto.event.CreateCustomerEvent;
import com.nova.customer_registration.dto.event.CustomerValidatedEvent;
import com.nova.customer_registration.service.CustomerValidationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerValidationServiceImpl implements CustomerValidationService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void validate(CreateCustomerEvent event) {
        log.info("SERVICIO DE VALIDATE -> Validando usuario: {}", event);
        log.info("SERVICIO DE VALIDATE -> Verificando nombre, email, datos etc...");

        CustomerValidatedEvent validatedEvent = new CustomerValidatedEvent(
                UUID.randomUUID().toString(),
                event.id(),
                StatusCustomerEnum.ACTIVO.getValue()
        );

        kafkaTemplate.send(
                TopicCustomerEnum.CUSTOMER_VALIDATED.getValue(),
                validatedEvent.eventId(),
                validatedEvent
        );

        log.info("SERVICIO DE VALIDATE -> Evento de validacion publicado: {}", validatedEvent);
    }
}
