package fhv.eventbus.dto;

import fhv.eventbus.entity.StoredEvent;

import java.time.Instant;

public record StoredEventDTO(
        Long id,
        String stream,
        String type,
        String payload,
        Instant timestamp
) {
    public static StoredEventDTO from(StoredEvent e) {
        return new StoredEventDTO(
                e.getId(),
                e.getStream(),
                e.getType(),
                e.getPayload(),
                e.getTimestamp()
        );
    }
}
