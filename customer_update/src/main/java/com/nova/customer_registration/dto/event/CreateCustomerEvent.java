package com.nova.customer_registration.dto.event;

public record CreateCustomerEvent(
        String eventId,
        Integer id,
        String nombre,
        String estatus
) {
}
