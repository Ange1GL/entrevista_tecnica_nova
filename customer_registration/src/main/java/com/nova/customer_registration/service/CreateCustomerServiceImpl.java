package com.nova.customer_registration.service;


import com.nova.customer_registration.constant.StatusCustomerEnum;
import com.nova.customer_registration.entity.CustomerEntity;
import com.nova.customer_registration.repository.CustomerRepository;
import com.nova.customer_registration.service.impl.CreateCustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCustomerServiceImpl implements CreateCustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public void create(String name) {
        CustomerEntity  customerEntity = new CustomerEntity();
        customerEntity.setName(name);
        customerEntity.setStatus(StatusCustomerEnum.ALTA.getValue());
        customerRepository.save(customerEntity);

        // aqui se publica el evento
    }
}
