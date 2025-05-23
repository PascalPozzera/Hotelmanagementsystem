package fhv.eventbus.client;

import at.fhv.sys.hotel.commands.shared.events.booking.BookingCancelled;
import at.fhv.sys.hotel.commands.shared.events.booking.BookingCreated;
import at.fhv.sys.hotel.commands.shared.events.booking.BookingPayed;
import at.fhv.sys.hotel.commands.shared.events.customer.*;
import at.fhv.sys.hotel.commands.shared.events.RoomCreated;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "hotel-query-api-client")
@Path("/api")
public interface QueryClient {

    @POST
    @Path("/customerCreated")
    @Consumes(MediaType.APPLICATION_JSON)
    void forwardCustomerCreatedEvent(CustomerCreated event);

    @POST
    @Path("/customerUpdated")
    @Consumes(MediaType.APPLICATION_JSON)
    void forwardCustomerUpdatedEvent(CustomerUpdated event);

    @POST
    @Path("/bookingCreated")
    @Consumes(MediaType.APPLICATION_JSON)
    void forwardBookingCreatedEvent(BookingCreated event);

    @POST
    @Path("/bookingCancelled")
    @Consumes(MediaType.APPLICATION_JSON)
    void forwardBookingCancelledEvent(BookingCancelled event);

    @POST
    @Path("/bookingPayed")
    @Consumes(MediaType.APPLICATION_JSON)
    void forwardBookingPayedEvent(BookingPayed event);

    @POST
    @Path("/roomCreated")
    @Consumes(MediaType.APPLICATION_JSON)
    void forwardRoomCreatedEvent(RoomCreated event);
}