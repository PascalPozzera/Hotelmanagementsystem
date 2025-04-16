package fhv.commands.booking;


import java.time.LocalDate;

public record CreateBookingCommand(
        int roomNumber,
        long customerId,
        LocalDate startDate,
        LocalDate endDate,
        int numberOfGuests
) {
}

