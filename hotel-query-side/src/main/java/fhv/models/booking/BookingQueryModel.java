package fhv.models.booking;

import at.fhv.sys.hotel.commands.shared.dto.booking.BookingResponseDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class BookingQueryModel {

    @Id
    private String bookingId;

    private int roomNumber;
    private String customerId;
    private LocalDate startDate;
    private LocalDate endDate;
    private int numberOfGuests;

    public BookingQueryModel() {}

    public BookingQueryModel(String bookingId, int roomNumber, String customerId,
                             LocalDate startDate, LocalDate endDate, int numberOfGuests) {
        this.bookingId = bookingId;
        this.roomNumber = roomNumber;
        this.customerId = customerId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.numberOfGuests = numberOfGuests;
    }

    public BookingResponseDTO toDTO() {
        return new BookingResponseDTO(
                this.bookingId,
                this.roomNumber,
                this.customerId,
                this.startDate,
                this.endDate,
                this.numberOfGuests
        );
    }

    @Override
    public String toString() {
        return "BookingQueryModel{" +
                "bookingId='" + bookingId + '\'' +
                ", roomNumber='" + roomNumber + '\'' +
                ", customerId='" + customerId + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", numberOfGuests=" + numberOfGuests +
                '}';
    }
}

//Remove Class??