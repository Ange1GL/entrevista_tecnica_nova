package com.nova.customer_update.service;


import com.nova.customer_update.constant.StatusCustomerEnum;
import com.nova.customer_update.constant.TopicCustomerEnum;
import com.nova.customer_update.dto.event.CreateCustomerEvent;
import com.nova.customer_update.entity.CustomerEntity;
import com.nova.customer_update.mapper.CustomerMapper;
import com.nova.customer_update.repository.CustomerRepository;
import com.nova.customer_update.service.impl.CreateCustomerService;
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
        // aqui se publica el evento
        kafkaTemplate.send(TopicCustomerEnum.ALTA_CUSTOMER.toString(), event.eventId(), event);
    }
}
