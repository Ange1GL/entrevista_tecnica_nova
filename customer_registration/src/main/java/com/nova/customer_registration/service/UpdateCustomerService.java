package com.nova.customer_registration.service;

import com.nova.customer_registration.dto.event.CustomerValidatedEvent;

public interface UpdateCustomerService {
    void updateStatus(CustomerValidatedEvent event);
}
