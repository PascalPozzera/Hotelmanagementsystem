package fhv.commands.booking;


import java.time.LocalDate;

public record CreateBookingCommand(
        int roomNumber,
        String email,
        LocalDate startDate,
        LocalDate endDate,
        int numberOfGuests
) {
}

