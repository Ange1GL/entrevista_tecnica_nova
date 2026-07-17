package com.nova.customer_registration.service.impl;

import com.nova.customer_registration.constant.StatusCustomerEnum;
import com.nova.customer_registration.dto.event.CustomerValidatedEvent;
import com.nova.customer_registration.entity.CustomerEntity;
import com.nova.customer_registration.repository.CustomerRepository;
import com.nova.customer_registration.service.UpdateCustomerService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateCustomerServiceImpl implements UpdateCustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public void updateStatus(CustomerValidatedEvent event) {
        log.info("Actualizando status del cliente con id: {}", event.id());
        CustomerEntity customer = customerRepository.findById(event.id())
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado con id: " + event.id()));
        customer.setStatus(StatusCustomerEnum.ACTIVO.getValue());
        customerRepository.save(customer);
        log.info("Cliente {} actualizado a status: {}", event.id(), StatusCustomerEnum.ACTIVO.getValue());
    }
}
