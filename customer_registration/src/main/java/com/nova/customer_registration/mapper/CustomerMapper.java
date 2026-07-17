package com.nova.customer_registration.mapper;

import com.nova.customer_registration.dto.event.CreateCustomerEvent;
import com.nova.customer_registration.entity.CustomerEntity;

import java.util.UUID;

public class CustomerMapper {

    private CustomerMapper() {}

    public static CreateCustomerEvent toEvent(CustomerEntity entity) {
        return new CreateCustomerEvent(
                UUID.randomUUID().toString(),
                entity.getId(),
                entity.getName(),
                entity.getStatus()
        );
    }
}
