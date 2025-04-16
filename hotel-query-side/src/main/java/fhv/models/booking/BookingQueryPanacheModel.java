package fhv.models.booking;

import at.fhv.sys.hotel.commands.shared.dto.booking.BookingResponseDTO;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

import java.time.LocalDate;
import java.util.UUID;

@Entity
public class BookingQueryPanacheModel extends PanacheEntity {

    public int roomNumber;
    public long customerId;
    public LocalDate startDate;
    public LocalDate endDate;
    public int numberOfGuests;

    public BookingQueryPanacheModel() {
    }

    public BookingQueryPanacheModel(int roomNumber, long customerId, LocalDate startDate, LocalDate endDate, int numberOfGuests) {

        this.roomNumber = roomNumber;
        this.customerId = customerId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.numberOfGuests = numberOfGuests;
    }

    public BookingResponseDTO toDTO() {
        return new BookingResponseDTO(
                this.roomNumber,
                this.customerId,
                this.startDate,
                this.endDate,
                this.numberOfGuests
        );
    }

    @Override
    public String toString() {
        return "BookingQueryPanacheModel{" +
                ", roomNumber='" + roomNumber + '\'' +
                ", customerId='" + customerId + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", numberOfGuests=" + numberOfGuests +
                '}';
    }
}
