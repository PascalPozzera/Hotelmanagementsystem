package at.fhv.sys.hotel.commands.shared.dto.booking;

import java.time.LocalDate;

public record BookingResponseDTO(int roomNumber,
                                 long customerId,
                                 LocalDate startDate,
                                 LocalDate endDate,
                                 int numberOfGuests) {
}
