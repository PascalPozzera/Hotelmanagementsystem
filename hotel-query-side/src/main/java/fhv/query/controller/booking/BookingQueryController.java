package fhv.query.controller.booking;

import at.fhv.sys.hotel.commands.shared.dto.booking.BookingResponseDTO;
import at.fhv.sys.hotel.commands.shared.events.booking.BookingCancelled;
import at.fhv.sys.hotel.commands.shared.events.booking.BookingCreated;
import at.fhv.sys.hotel.commands.shared.events.booking.BookingPayed;
import fhv.projection.booking.BookingProjection;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.jboss.logmanager.Logger;

import java.time.LocalDate;
import java.util.List;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookingQueryController {

    @Inject
    BookingProjection bookingProjection;

    @GET
    @Path("/getBookings")
    public Response getBookings(@QueryParam("from") LocalDate from, @QueryParam("to") LocalDate to) {
        List<BookingResponseDTO> bookings = bookingProjection.getBookingsInDateRange(from, to);
        return Response.ok(bookings).build();
    }

    @POST
    @Operation(hidden = true) //The method is hidden to prevent it from being visible to the user in Swagger.
    @Path("/bookingCreated")
    public Response bookingCreated(BookingCreated event) {
        Logger.getAnonymousLogger().info("Received event: " + event);
        bookingProjection.processBookingCreatedEvent(event);
        return Response.ok(event).build();
    }

    @POST
    @Operation(hidden = true) //The method is hidden to prevent it from being visible to the user in Swagger.
    @Path("/bookingPayed")
    public Response bookingPayed(BookingPayed event) {
        Logger.getAnonymousLogger().info("Received event: " + event);
        bookingProjection.processBookingPayedEvent(event);
        return Response.ok(event).build();
    }

    @POST
    @Operation(hidden = true) //The method is hidden to prevent it from being visible to the user in Swagger.
    @Path("/bookingCancelled")
    public Response bookingCancelled(BookingCancelled event) {
        Logger.getAnonymousLogger().info("Received event: " + event);
        bookingProjection.processBookingCancelledEvent(event);
        return Response.ok(event).build();
    }
}
