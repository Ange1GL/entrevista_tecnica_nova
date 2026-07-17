package com.nova.customer_registration.service;


import com.nova.customer_registration.dto.event.CreateCustomerEvent;

public interface CustomerValidationService {

    void validate(CreateCustomerEvent event);
}
