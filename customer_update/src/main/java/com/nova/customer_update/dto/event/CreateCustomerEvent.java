package com.nova.customer_update.dto.event;

public record CreateCustomerEvent(
        String eventId,
        Integer id,
        String nombre,
        String estatus
) {
}
