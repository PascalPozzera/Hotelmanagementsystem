package fhv.controller;

import at.fhv.sys.hotel.commands.shared.dto.booking.BookingCancelRequestDTO;
import at.fhv.sys.hotel.commands.shared.dto.booking.BookingPayRequestDTO;
import at.fhv.sys.hotel.commands.shared.dto.booking.BookingRequestDTO;
import fhv.commands.booking.BookingAggregate;
import fhv.commands.booking.CancelBookingCommand;
import fhv.commands.booking.CreateBookingCommand;
import fhv.commands.booking.PayBookingCommand;
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
    @Path("/cancelBooking")
    public String cancelBooking(@BeanParam BookingCancelRequestDTO requestDTO) {
        return bookingAggregate.handle((new CancelBookingCommand(
                requestDTO.getBookingId(),
                requestDTO.getEmail(),
                requestDTO.getRoomNumber()
        )));
    }

    @POST
    @Path("/payBooking")
    public String payBooking(@BeanParam BookingPayRequestDTO requestDTO) {
        return bookingAggregate.handle(new PayBookingCommand(
                requestDTO.getBookingId(),
                requestDTO.getEmail(),
                requestDTO.getRoomNumber(),
                requestDTO.getPaymentMethod()));
    }
}
