package at.fhv.sys.hotel.commands.shared.dto.booking;

import at.fhv.sys.hotel.commands.shared.enums.PaymentMethod;

import java.time.LocalDate;
import java.util.UUID;

public record BookingResponseDTO(UUID bookingId,
                                 int roomNumber,
                                 String email,
                                 LocalDate startDate,
                                 LocalDate endDate,
                                 int numberOfGuests,
                                 boolean isPayed,
                                 PaymentMethod paymentMethod) {
}
