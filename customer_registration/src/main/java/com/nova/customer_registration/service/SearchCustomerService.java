package com.nova.customer_registration.service;

import com.nova.customer_registration.dto.response.CustomerResponse;

public interface SearchCustomerService {

    CustomerResponse searchCustomerByName(String name);
}
