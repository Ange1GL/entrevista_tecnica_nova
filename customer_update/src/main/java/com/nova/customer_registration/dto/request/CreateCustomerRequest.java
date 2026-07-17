package com.nova.customer_registration.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateCustomerRequest(

        @NotNull(message = "La propiedad 'nombre' no debe ser null")
        @NotBlank(message = "La propiedad 'nombre' no debe ser vacía")
        String nombre
) {
}
