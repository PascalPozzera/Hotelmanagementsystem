package at.fhv.sys.hotel.commands.shared.dto.booking;

import java.time.LocalDate;

public record BookingResponseDTO(String bookingId,
                                 int roomNumber,
                                 String customerId,
                                 LocalDate startDate,
                                 LocalDate endDate,
                                 int numberOfGuests) {
}
