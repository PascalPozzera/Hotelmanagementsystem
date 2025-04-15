package fhv.controller;

import fhv.commands.booking.BookingAggregate;
import fhv.commands.booking.CreateBookingCommand;
import fhv.dto.requestDTO.booking.BookingRequestDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.UUID;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookingCommandController {

    @Inject
    BookingAggregate bookingAggregate;

    @POST
    @Path("/bookRoom")
    public String bookRoom(@BeanParam BookingRequestDTO requestDTO) {
        // TBD: fix date
        String generatedId = UUID.randomUUID().toString();
        return bookingAggregate.handle(new CreateBookingCommand(
                generatedId,
                requestDTO.getRoomNumber(),
                requestDTO.getCustomerId(),
                requestDTO.getStartDate(),
                requestDTO.getEndDate(),
                requestDTO.getNumberOfGuests()));
    }

    @POST
    @Path("/{bookingId}/cancelBooking")
    public String deleteBooking(@PathParam("bookingId") String bookingId) {
        // TBD: cancel booking
        return "Booking cancelled";
    }

    @POST
    @Path("/{bookingId}/payBooking")
    public String payBooking(@PathParam("bookingId") String bookingId) {
        // TBD: pay booking
        return "Booking payed";
    }
}
