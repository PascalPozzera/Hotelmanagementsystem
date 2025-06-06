package fhv.client;

import at.fhv.sys.hotel.commands.shared.events.booking.BookingCancelled;
import at.fhv.sys.hotel.commands.shared.events.booking.BookingCreated;
import at.fhv.sys.hotel.commands.shared.events.booking.BookingPayed;
import at.fhv.sys.hotel.commands.shared.events.customer.CustomerCreated;
import at.fhv.sys.hotel.commands.shared.events.RoomCreated;
import at.fhv.sys.hotel.commands.shared.events.customer.CustomerUpdated;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import jakarta.ws.rs.Path;

@RegisterRestClient(configKey = "hotel-eventbus-api-client")
@Path("/api")
public interface EventBusClient {

    @POST
    @Path("/customerCreated")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    CustomerCreated processCustomerCreatedEvent(CustomerCreated event);

    @POST
    @Path("/customerUpdated")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    CustomerCreated processCustomerUpdatedEvent(CustomerUpdated event);

    @POST
    @Path("/bookingCreated")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    BookingCreated processBookingCreatedEvent(BookingCreated event);

    @POST
    @Path("/bookingCancelled")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    BookingCancelled processBookingCancelledEvent(BookingCancelled event);

    @POST
    @Path("/bookingPayed")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    BookingPayed processBookingPayedEvent(BookingPayed event);

    @POST
    @Path("/roomCreated")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    RoomCreated processRoomCreatedEvent(RoomCreated event);
}
