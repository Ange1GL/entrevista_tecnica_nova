package com.nova.customer_registration.service;

import com.nova.customer_registration.constant.StatusCustomerEnum;
import com.nova.customer_registration.entity.CustomerEntity;
import com.nova.customer_registration.repository.CustomerRepository;
import com.nova.customer_registration.service.impl.CreateCustomerServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateCustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

//    @Mock
//    private KafkaTemplate<String, Object> kafkaTemplate;

    @InjectMocks
    private CreateCustomerServiceImpl createCustomerService;

    @Test
    @DisplayName("Debe guardar un usuario en status: ALTA")
    public void shouldSaveCustomerAlta() {

        // preparamos el dato de entrada
        String nombreTest = "Angel";

        // creamos la entidad que el mock de repository va a "devolver" al llamar save()
        CustomerEntity entityGuardada = new CustomerEntity();
        entityGuardada.setId(1);
        entityGuardada.setName(nombreTest);
        entityGuardada.setStatus(StatusCustomerEnum.ALTA.getValue());

        // le decimos al mock: cuando alguien llame save() con cualquier CustomerEntity,
        // devuelve la entidad que ya preparamos arriba (simulamos que la BD generó el id)
        when(customerRepository.save(any(CustomerEntity.class))).thenReturn(entityGuardada);

        // ejecutamos el método que queremos testear
        createCustomerService.create(nombreTest);

        // capturamos el argumento real que recibió save() para poder inspeccionarlo
        ArgumentCaptor<CustomerEntity> captor = ArgumentCaptor.forClass(CustomerEntity.class);
        verify(customerRepository).save(captor.capture());

        // sacamos la entidad capturada y verificamos campo por campo
        CustomerEntity entityCapturada = captor.getValue();
        assertEquals(nombreTest, entityCapturada.getName());
        assertEquals(StatusCustomerEnum.ALTA.getValue(), entityCapturada.getStatus());
    }
}
