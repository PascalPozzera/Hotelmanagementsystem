package fhv.commands.booking;


import java.time.LocalDate;

public record CreateBookingCommand(
        String bookingId,
        String roomNumber,
        String customerId,
        LocalDate startDate,
        LocalDate endDate,
        int numberOfGuests
) {}

