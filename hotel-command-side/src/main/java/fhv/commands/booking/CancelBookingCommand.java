package fhv.commands.booking;


import java.util.UUID;

public record CancelBookingCommand(UUID bookingId,
                                   String email,
                                   int roomNumber) {
}