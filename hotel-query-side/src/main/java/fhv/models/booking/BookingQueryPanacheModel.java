package fhv.models.booking;

import at.fhv.sys.hotel.commands.shared.dto.BookingResponseDTO;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
public class BookingQueryPanacheModel extends PanacheEntity {


    // Panache provides Auto-CRUD for everything
    public String bookingId;
    public String roomNumber;
    public String customerId;
    public LocalDate startDate;
    public LocalDate endDate;
    public int numberOfGuests;

    public BookingQueryPanacheModel() {
    }

    public BookingQueryPanacheModel(String bookingId, String roomNumber, String customerId, LocalDate startDate, LocalDate endDate, int numberOfGuests) {
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
        return "BookingQueryPanacheModel{" +
                "bookingId='" + bookingId + '\'' +
                ", roomNumber='" + roomNumber + '\'' +
                ", customerId='" + customerId + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", numberOfGuests=" + numberOfGuests +
                '}';
    }
}
