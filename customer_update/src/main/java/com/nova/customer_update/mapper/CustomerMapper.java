package com.nova.customer_update.mapper;

import com.nova.customer_update.dto.event.CreateCustomerEvent;
import com.nova.customer_update.entity.CustomerEntity;

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
