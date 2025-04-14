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
    @Path("/createBooking")
    public String createBooking(@BeanParam BookingRequestDTO requestDTO) {
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
    @Path("/{bookingId}/update")
    public String updateBooking(@PathParam("bookingId") String bookingId, @BeanParam BookingRequestDTO requestDTO) {
        // TBD: process booking
        return "Booking updated";
    }

    @POST
    @Path("/{bookingId}/delete")
    public String deleteBooking(@PathParam("bookingId") String bookingId) {
        // TBD: delete booking
        return "Booking deleted";
    }
}
