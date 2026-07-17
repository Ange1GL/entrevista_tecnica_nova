package com.nova.customer_update.dto.event;

public record CustomerValidatedEvent(
        String eventId,
        Integer id,
        String estatus
) {
}
