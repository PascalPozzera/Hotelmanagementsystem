package fhv.eventbus.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import fhv.eventbus.entity.StoredEvent;
import fhv.eventbus.repo.StoredEventRepo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class EventStoreService {

    @Inject
    StoredEventRepo storedEventRepo;

    private final ObjectMapper objectMapper = new ObjectMapper();

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
