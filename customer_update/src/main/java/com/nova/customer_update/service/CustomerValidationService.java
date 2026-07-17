package com.nova.customer_update.service;


import com.nova.customer_update.dto.event.CreateCustomerEvent;

public interface CustomerValidationService {

    void validate(CreateCustomerEvent event);
}
