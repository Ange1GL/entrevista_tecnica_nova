package com.nova.customer_registration.dto.event;

public record CustomerValidatedEvent(
        String eventId,
        Integer id,
        String estatus
) {
}
