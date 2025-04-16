package fhv.commands.booking;


import java.time.LocalDate;

public record CreateBookingCommand(
        String bookingId,
        int roomNumber,
        String customerId,
        LocalDate startDate,
        LocalDate endDate,
        int numberOfGuests
) {}

