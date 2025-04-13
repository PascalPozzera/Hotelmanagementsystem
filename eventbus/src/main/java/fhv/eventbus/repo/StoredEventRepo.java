package fhv.eventbus.repo;

import fhv.eventbus.entity.StoredEvent;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class StoredEventRepo implements PanacheRepository<StoredEvent> {
}
