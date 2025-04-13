package fhv.services;


import at.fhv.sys.hotel.commands.shared.events.CustomerCreated;
import fhv.client.QueryClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class EventProcessingService {

    @Inject
    @RestClient
    QueryClient queryClient;

    public EventProcessingService() {
    }

    public void processEvent(String stream, Object eventObject) {
        queryClient.forwardCustomerCreatedEvent((CustomerCreated) eventObject);
        // TBD process Events in EventDBStore
    }
}
