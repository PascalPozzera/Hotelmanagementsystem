package at.fhv.sys.hotel.commands.shared.dto.booking;

import at.fhv.sys.hotel.commands.shared.enums.PaymentMethod;
import jakarta.ws.rs.QueryParam;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class BookingPayRequestDTO {

    @QueryParam("bookingId")
    private UUID bookingId;

    @QueryParam("customerEmail")
    private String email;

    @QueryParam("roomNumber")
    private int roomNumber;

    @QueryParam("paymentMethod")
    private PaymentMethod paymentMethod;
}