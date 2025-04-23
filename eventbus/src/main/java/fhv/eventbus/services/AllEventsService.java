package fhv.eventbus.services;

import fhv.eventbus.dto.StoredEventDTO;
import fhv.eventbus.repo.StoredEventRepo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class AllEventsService {

    @Inject
    StoredEventRepo storedEventRepo;

    @Transactional
    public List<StoredEventDTO> getAllEvents() {
        return storedEventRepo.listAll().stream().map(StoredEventDTO::from).toList();
    }
}
