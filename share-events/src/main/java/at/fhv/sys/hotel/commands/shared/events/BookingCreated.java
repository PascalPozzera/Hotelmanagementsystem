package at.fhv.sys.hotel.commands.shared.events;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class BookingCreated {

    private String bookingId;
    private String roomNumber;
    private String customerId;
    private LocalDate startDate;
    private LocalDate endDate;
    private int numberOfGuests;

    public BookingCreated(
            String bookingId,
            String roomNumber,
            String customerId,
            LocalDate startDate,
            LocalDate endDate,
            int numberOfGuests) {

        this.bookingId = bookingId;
        this.roomNumber = roomNumber;
        this.customerId = customerId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.numberOfGuests = numberOfGuests;

    }

    @Override
    public String toString() {
        return "BookingCreated{" +
                "bookingId='" + bookingId + '\'' +
                ", roomNumber='" + roomNumber + '\'' +
                ", customerId='" + customerId + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", numberOfGuests=" + numberOfGuests +
                '}';
    }
}
