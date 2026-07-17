package com.nova.customer_registration.service;

import com.nova.customer_registration.constant.StatusCustomerEnum;
import com.nova.customer_registration.entity.CustomerEntity;
import com.nova.customer_registration.repository.CustomerRepository;
import com.nova.customer_registration.service.impl.CreateCustomerServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateCustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private KafkaTemplate<String, Object> kafkaTemplate;

    @InjectMocks
    private CreateCustomerServiceImpl createCustomerService;

    @Test
    @DisplayName("Debe guardar un usuario en status: ALTA")
    public void shouldSaveCustomerAlta() {

        // preparamos el dato de entrada
        String nombreTest = "Angel";


        CustomerEntity entityGuardada = new CustomerEntity();
        entityGuardada.setId(1);
        entityGuardada.setName(nombreTest);
        entityGuardada.setStatus(StatusCustomerEnum.ALTA.getValue());


        when(customerRepository.save(any(CustomerEntity.class))).thenReturn(entityGuardada);


        when(kafkaTemplate.send(anyString(), anyString(), any())).thenReturn(null);

        createCustomerService.create(nombreTest);
        assertEquals(nombreTest, entityGuardada.getName());
        assertEquals(StatusCustomerEnum.ALTA.getValue(), entityGuardada.getStatus());
    }
}
