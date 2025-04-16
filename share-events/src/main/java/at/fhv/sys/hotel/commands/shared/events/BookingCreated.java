package at.fhv.sys.hotel.commands.shared.events;

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
    private long customerId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isPayed;
    private int numberOfGuests;

    public BookingCreated(
            int roomNumber,
            long customerId,
            LocalDate startDate,
            LocalDate endDate,
            boolean isPayed,
            int numberOfGuests) {
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
                ", roomNumber='" + roomNumber + '\'' +
                ", customerId='" + customerId + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", isPayed=" + isPayed +
                ", numberOfGuests=" + numberOfGuests +
                '}';
    }
}
