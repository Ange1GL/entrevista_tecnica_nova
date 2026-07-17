package com.nova.customer_update.service;

import com.nova.customer_update.dto.event.CustomerValidatedEvent;

public interface UpdateCustomerService {
    void updateStatus(CustomerValidatedEvent event);
}
