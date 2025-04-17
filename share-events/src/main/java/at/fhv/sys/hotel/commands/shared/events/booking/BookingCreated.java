package at.fhv.sys.hotel.commands.shared.events.booking;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class BookingCreated {

    private UUID bookingId;
    private int roomNumber;
    private String email;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isPayed;
    private int numberOfGuests;

    public BookingCreated(
            UUID bookingId,
            int roomNumber,
            String email,
            LocalDate startDate,
            LocalDate endDate,
            boolean isPayed,
            int numberOfGuests) {
        this.bookingId = bookingId;
        this.roomNumber = roomNumber;
        this.email = email;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isPayed = isPayed;
        this.numberOfGuests = numberOfGuests;

    }

    @Override
    public String toString() {
        return "BookingCreated{" +
                ", roomNumber='" + roomNumber + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", isPayed=" + isPayed +
                ", numberOfGuests=" + numberOfGuests +
                '}';
    }
}
