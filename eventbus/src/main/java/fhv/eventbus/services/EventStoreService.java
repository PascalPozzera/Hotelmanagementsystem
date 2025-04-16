package fhv.eventbus.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.SerializationFeature;
import fhv.eventbus.entity.StoredEvent;
import fhv.eventbus.repo.StoredEventRepo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class EventStoreService {

    @Inject
    StoredEventRepo storedEventRepo;

    //Is needed because of LocalDateTime
    private final ObjectMapper objectMapper;

    public EventStoreService() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
        this.objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Transactional
    public void storeEvent(String stream, Object event) {
        try {
            String payload = objectMapper.writeValueAsString(event);
            String type = event.getClass().getSimpleName();
            StoredEvent stored = new StoredEvent(stream, type, payload);
            storedEventRepo.persist(stored);
        } catch (Exception e) {
            throw new RuntimeException("Could not store event", e);
        }
    }
}
