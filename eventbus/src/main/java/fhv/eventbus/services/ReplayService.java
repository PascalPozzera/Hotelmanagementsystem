package fhv.eventbus.services;

import at.fhv.sys.hotel.commands.shared.events.BookingCreated;
import at.fhv.sys.hotel.commands.shared.events.CustomerCreated;
import com.fasterxml.jackson.databind.ObjectMapper;
import fhv.eventbus.client.QueryClient;
import fhv.eventbus.entity.StoredEvent;
import fhv.eventbus.repo.StoredEventRepo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import java.util.List;

@ApplicationScoped
public class ReplayService {

    private static final Logger LOG = Logger.getLogger(ReplayService.class);

    @Inject
    StoredEventRepo repository;

    @Inject
    @RestClient // -> weil @RegisterRestClient(configKey = "hotel-query-api-client") Markus ;) verwaltet diesen Client nicht als @Default Bean, sondern nur, wenn dieser mit @RestClient injected wird.
    QueryClient queryClient;

    private final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule())
            .disable(com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    @Transactional
    public Response replayAllEvents() {

        List<StoredEvent> events = repository.listAll();

        for (StoredEvent event : events) {
            try {
                String type = event.getType();
                String payload = event.getPayload();

                switch (type) {
                    case "CustomerCreated" -> {
                        CustomerCreated customerCreated = objectMapper.readValue(payload, CustomerCreated.class);
                        queryClient.forwardCustomerCreatedEvent(customerCreated);
                        LOG.info("Replayed: " + type + " -> " + customerCreated.getEmail());
                    }
                    case "BookingCreated" -> {
                        BookingCreated bookingCreated = objectMapper.readValue(payload, BookingCreated.class);
                        queryClient.forwardBookingCreatedEvent(bookingCreated);
                        LOG.info("Replayed: " + type + " -> " + bookingCreated.getEmail());
                    }
                    default -> LOG.warn("Unknown event type: " + type);
                }

            } catch (Exception e) {
                LOG.error("Failed to replay event: " + event.getId(), e);
                return Response.serverError().build();
            }
        }
        return Response.ok().build();
    }
}

