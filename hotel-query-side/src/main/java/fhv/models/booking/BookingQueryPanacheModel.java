package fhv.models.booking;

import at.fhv.sys.hotel.commands.shared.dto.booking.BookingResponseDTO;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.util.UUID;

@Entity
public class BookingQueryPanacheModel extends PanacheEntityBase {

    @Id
    public UUID bookingId;
    public int roomNumber;
    public String email;
    public LocalDate startDate;
    public LocalDate endDate;
    public int numberOfGuests;

    public BookingQueryPanacheModel() {
    }

    public BookingQueryPanacheModel(UUID bookingId, int roomNumber, String email, LocalDate startDate, LocalDate endDate, int numberOfGuests) {
        this.bookingId = bookingId;
        this.roomNumber = roomNumber;
        this.email = email;
        this.startDate = startDate;
        this.endDate = endDate;
        this.numberOfGuests = numberOfGuests;
    }

    public BookingResponseDTO toDTO() {
        return new BookingResponseDTO(
                this.bookingId,
                this.roomNumber,
                this.email,
                this.startDate,
                this.endDate,
                this.numberOfGuests
        );
    }

    @Override
    public String toString() {
        return "BookingQueryPanacheModel{" +
                "bookingId=" + bookingId +
                ", roomNumber=" + roomNumber +
                ", email='" + email + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", numberOfGuests=" + numberOfGuests +
                '}';
    }
}
