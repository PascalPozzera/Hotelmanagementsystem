package at.fhv.sys.hotel.commands.shared.events.booking;

import at.fhv.sys.hotel.commands.shared.enums.PaymentMethod;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class BookingPayed {

    private UUID bookingId;
    private String email;
    private int roomNumber;
    private boolean isPayed;
    private PaymentMethod paymentMethod;

    public BookingPayed(UUID bookingId, String email, int roomNumber, boolean isPayed, PaymentMethod paymentMethod) {
        this.bookingId = bookingId;
        this.email = email;
        this.roomNumber = roomNumber;
        this.isPayed = isPayed;
        this.paymentMethod = paymentMethod;
    }

    @Override
    public String toString() {
        return "BookingPayed{" +
                "bookingId=" + bookingId +
                ", email='" + email + '\'' +
                ", roomNumber=" + roomNumber +
                ", isPayed=" + isPayed +
                ", paymentMethod=" + paymentMethod +
                '}';
    }
}
