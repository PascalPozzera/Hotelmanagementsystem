package at.fhv.sys.hotel.commands.shared.dto.booking;

import java.time.LocalDate;

public record BookingResponseDTO(int roomNumber,
                                 String email,
                                 LocalDate startDate,
                                 LocalDate endDate,
                                 int numberOfGuests) {
}
