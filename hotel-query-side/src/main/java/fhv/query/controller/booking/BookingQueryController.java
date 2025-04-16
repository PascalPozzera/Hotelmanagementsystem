package fhv.query.controller.booking;

import at.fhv.sys.hotel.commands.shared.dto.booking.BookingResponseDTO;
import at.fhv.sys.hotel.commands.shared.events.BookingCreated;
import fhv.projection.booking.BookingProjection;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.jboss.logmanager.Logger;

import java.util.List;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookingQueryController {

    @Inject
    BookingProjection bookingProjection;

    @GET
    @Path("/getBookings")
    public Response getBookings() {
        List<BookingResponseDTO> bookings = bookingProjection.getAllBookings();
        return Response.ok(bookings).build();
    }

    @POST
    @Operation(hidden = true) //The POST method is hidden to prevent it from being visible to the user in Swagger.
    @Path("/bookingCreated")
    public Response bookingCreated(BookingCreated event) {
        Logger.getAnonymousLogger().info("Received event: " + event);
        bookingProjection.processBookingCreatedEvent(event);
        return Response.ok(event).build();
    }
}
