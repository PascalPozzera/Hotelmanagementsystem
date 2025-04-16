package at.fhv.sys.hotel.commands.shared.dto;

import java.time.LocalDate;

public record BookingResponseDTO(String bookingId,
                                 String roomNumber,
                                 String customerId,
                                 LocalDate startDate,
                                 LocalDate endDate,
                                 int numberOfGuests) {
}
