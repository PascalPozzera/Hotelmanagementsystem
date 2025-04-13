package fhv.eventbus.entity;

import java.time.Instant;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "stored_events")
public class StoredEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String stream;

    @Lob
    private String payload;

    private String type;

    private Instant timestamp;

    public StoredEvent() {
    }

    public StoredEvent(String stream, String type, String payload) {
        this.stream = stream;
        this.type = type;
        this.payload = payload;
        this.timestamp = Instant.now();
    }
}
