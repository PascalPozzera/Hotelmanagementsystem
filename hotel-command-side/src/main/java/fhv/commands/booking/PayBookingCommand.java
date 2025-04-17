package fhv.commands.booking;


import at.fhv.sys.hotel.commands.shared.enums.PaymentMethod;

import java.util.UUID;

public record PayBookingCommand(UUID bookingId,
                                String email,
                                int roomNumber,
                                PaymentMethod paymentMethod) {
}