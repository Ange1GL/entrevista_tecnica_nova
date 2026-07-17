package com.nova.customer_registration.service.impl;


import com.nova.customer_registration.constant.StatusCustomerEnum;
import com.nova.customer_registration.constant.TopicCustomerEnum;
import com.nova.customer_registration.dto.event.CreateCustomerEvent;
import com.nova.customer_registration.entity.CustomerEntity;
import com.nova.customer_registration.mapper.CustomerMapper;
import com.nova.customer_registration.repository.CustomerRepository;
import com.nova.customer_registration.service.CreateCustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCustomerServiceImpl implements CreateCustomerService {

    private final CustomerRepository customerRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;


    @Override
    public void create(String name) {
        CustomerEntity  customerEntity = new CustomerEntity();
        customerEntity.setName(name);
        customerEntity.setStatus(StatusCustomerEnum.ALTA.getValue());
        customerRepository.save(customerEntity);

        CreateCustomerEvent event = CustomerMapper.toEvent(customerEntity);
        kafkaTemplate.send(TopicCustomerEnum.ALTA_CUSTOMER.getValue(), event.eventId(), event);
    }
}
