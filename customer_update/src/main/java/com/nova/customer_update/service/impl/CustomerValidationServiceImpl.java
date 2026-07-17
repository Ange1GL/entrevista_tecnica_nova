package com.nova.customer_update.service.impl;

import com.nova.customer_update.dto.event.CreateCustomerEvent;
import com.nova.customer_update.service.CustomerValidationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
// este service valida la lógica de un usuairo creado
@Slf4j
public class CustomerValidationServiceImpl
implements CustomerValidationService
{

    @Override
    public void validate(CreateCustomerEvent event) {


        log.info("SERVICIO DE VALIDATE -> Evento validado: {}", event);
        log.info("SERVICIO DE VALIDATE -> Realizando logica como verificar nombre, email, datos etc");


    }
}
