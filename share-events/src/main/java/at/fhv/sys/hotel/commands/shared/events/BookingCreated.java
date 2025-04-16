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
    private int roomNumber;
    private String customerId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isPayed;
    private int numberOfGuests;

    public BookingCreated(
            String bookingId,
            int roomNumber,
            String customerId,
            LocalDate startDate,
            LocalDate endDate,
            boolean isPayed,
            int numberOfGuests) {

        this.bookingId = bookingId;
        this.roomNumber = roomNumber;
        this.customerId = customerId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isPayed = isPayed;
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
                ", isPayed=" + isPayed +
                ", numberOfGuests=" + numberOfGuests +
                '}';
    }
}
