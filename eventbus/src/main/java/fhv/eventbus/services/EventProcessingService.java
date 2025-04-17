package fhv.eventbus.services;


import at.fhv.sys.hotel.commands.shared.events.booking.BookingCancelled;
import at.fhv.sys.hotel.commands.shared.events.booking.BookingCreated;
import at.fhv.sys.hotel.commands.shared.events.CustomerCreated;
import at.fhv.sys.hotel.commands.shared.events.RoomCreated;
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
            case CustomerCreated customer -> queryClient.forwardCustomerCreatedEvent(customer);
            case BookingCreated booking -> queryClient.forwardBookingCreatedEvent(booking);
            case BookingCancelled bookingCancelled -> queryClient.forwardBookingCancelledEvent(bookingCancelled);
            case RoomCreated room -> queryClient.forwardRoomCreatedEvent(room);
            default -> System.out.println("Unknown event type: " + eventObject.getClass().getSimpleName());
        }
    }
}
