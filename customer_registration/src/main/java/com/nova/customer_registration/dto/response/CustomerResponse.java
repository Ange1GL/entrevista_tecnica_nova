package com.nova.customer_registration.dto.response;

public record CustomerResponse(
        Integer id,
        String nombre,
        String status
) {
}
