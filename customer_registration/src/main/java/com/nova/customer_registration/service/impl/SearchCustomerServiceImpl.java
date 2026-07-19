package com.nova.customer_registration.service.impl;


import com.nova.customer_registration.dto.response.CustomerResponse;
import com.nova.customer_registration.entity.CustomerEntity;
import com.nova.customer_registration.repository.CustomerRepository;
import com.nova.customer_registration.service.SearchCustomerService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SearchCustomerServiceImpl
implements SearchCustomerService
{
    private final CustomerRepository customerRepository;


    @Override
    public CustomerResponse searchCustomerByName(String name) {
        CustomerEntity customer = customerRepository.findFirstByNameContainsCustom(name).orElseThrow(
                () -> new EntityNotFoundException("Customer with name " + name + " not found")
        );

        return new CustomerResponse(
                customer.getId(),
                customer.getName(),
                customer.getStatus()
        );
    }
}
