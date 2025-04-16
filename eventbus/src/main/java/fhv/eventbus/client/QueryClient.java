package fhv.eventbus.client;

import at.fhv.sys.hotel.commands.shared.events.BookingCreated;
import at.fhv.sys.hotel.commands.shared.events.CustomerCreated;
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
    @Path("/bookingCreated")
    @Consumes(MediaType.APPLICATION_JSON)
    void forwardBookingCreatedEvent(BookingCreated event);

    @POST
    @Path("/roomCreated")
    @Consumes(MediaType.APPLICATION_JSON)
    void forwardRoomCreatedEvent(RoomCreated event);
}