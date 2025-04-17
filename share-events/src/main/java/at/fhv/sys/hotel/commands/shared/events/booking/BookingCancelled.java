package at.fhv.sys.hotel.commands.shared.events.booking;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class BookingCancelled {

    private UUID bookingId;
    private String email;
    private int roomNumber;

    public BookingCancelled(UUID bookingId, String email, int roomNumber) {
        this.bookingId = bookingId;
        this.email = email;
        this.roomNumber = roomNumber;
    }

    @Override
    public String toString() {
        return "BookingCancelled{" +
                ", bookingId=" + bookingId +
                ", email='" + email + '\'' +
                ", roomNumber=" + roomNumber +
                '}';
    }
}
