package fhv.eventbus.services;


import at.fhv.sys.hotel.commands.shared.events.BookingCreated;
import at.fhv.sys.hotel.commands.shared.events.CustomerCreated;
import fhv.eventbus.client.QueryClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class EventProcessingService {

    @Inject
    EventStoreService eventStoreService;

    @Inject
    @RestClient
    QueryClient queryClient;

    public EventProcessingService() {
    }

    public void processEvent(String stream, Object eventObject) {
        eventStoreService.storeEvent(stream, eventObject);

        switch (eventObject) {
            case CustomerCreated c -> queryClient.forwardCustomerCreatedEvent(c);
            case BookingCreated b -> queryClient.forwardBookingCreatedEvent(b);
            default -> System.out.println("Unknown event type: " + eventObject.getClass().getSimpleName());
        }
    }
}
