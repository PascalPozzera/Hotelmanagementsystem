package fhv.models.booking;

import at.fhv.sys.hotel.commands.shared.dto.booking.BookingResponseDTO;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
public class BookingQueryPanacheModel extends PanacheEntity {

    public int roomNumber;
    public String email;
    public LocalDate startDate;
    public LocalDate endDate;
    public int numberOfGuests;

    public BookingQueryPanacheModel() {
    }

    public BookingQueryPanacheModel(int roomNumber, String email, LocalDate startDate, LocalDate endDate, int numberOfGuests) {

        this.roomNumber = roomNumber;
        this.email = email;
        this.startDate = startDate;
        this.endDate = endDate;
        this.numberOfGuests = numberOfGuests;
    }

    public BookingResponseDTO toDTO() {
        return new BookingResponseDTO(
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
                ", roomNumber='" + roomNumber + '\'' +
                ", customerId='" + email + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", numberOfGuests=" + numberOfGuests +
                '}';
    }
}
