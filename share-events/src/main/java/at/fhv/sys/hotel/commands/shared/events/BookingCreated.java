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

    private int roomNumber;
    private String email;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isPayed;
    private int numberOfGuests;

    public BookingCreated(
            int roomNumber,
            String email,
            LocalDate startDate,
            LocalDate endDate,
            boolean isPayed,
            int numberOfGuests) {
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
