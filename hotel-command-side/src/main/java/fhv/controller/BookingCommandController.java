package fhv.controller;

import at.fhv.sys.hotel.commands.shared.dto.booking.BookingRequestDTO;
import fhv.commands.booking.BookingAggregate;
import fhv.commands.booking.CreateBookingCommand;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookingCommandController {

    @Inject
    BookingAggregate bookingAggregate;

    @POST
    @Path("/bookRoom")
    public String bookRoom(@BeanParam BookingRequestDTO requestDTO) {
        return bookingAggregate.handle(new CreateBookingCommand(
                requestDTO.getRoomNumber(),
                requestDTO.getEmail(),
                requestDTO.getStartDate(),
                requestDTO.getEndDate(),
                requestDTO.getNumberOfGuests()));
    }

    @POST
    @Path("/{bookingId}/cancelBooking")
    public String cancelBooking(@PathParam("bookingId") String bookingId) {
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
