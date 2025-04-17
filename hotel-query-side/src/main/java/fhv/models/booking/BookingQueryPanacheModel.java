package fhv.models.booking;

import at.fhv.sys.hotel.commands.shared.dto.booking.BookingResponseDTO;
import at.fhv.sys.hotel.commands.shared.enums.PaymentMethod;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    public boolean isPayed;

    @Enumerated(EnumType.STRING)
    public PaymentMethod paymentMethod;

    public BookingQueryPanacheModel() {
    }

    public BookingQueryPanacheModel(UUID bookingId, int roomNumber, String email, LocalDate startDate, LocalDate endDate, int numberOfGuests, boolean isPayed, PaymentMethod paymentMethod) {
        this.bookingId = bookingId;
        this.roomNumber = roomNumber;
        this.email = email;
        this.startDate = startDate;
        this.endDate = endDate;
        this.numberOfGuests = numberOfGuests;
        this.isPayed = isPayed;
        this.paymentMethod = paymentMethod;
    }

    public BookingQueryPanacheModel(UUID bookingId, int roomNumber, String email, boolean isPayed, PaymentMethod paymentMethod) {
        this.bookingId = bookingId;
        this.roomNumber = roomNumber;
        this.email = email;
        this.isPayed = isPayed;
        this.paymentMethod = paymentMethod;
    }

    public BookingResponseDTO toDTO() {
        return new BookingResponseDTO(
                this.bookingId,
                this.roomNumber,
                this.email,
                this.startDate,
                this.endDate,
                this.numberOfGuests,
                this.isPayed,
                this.paymentMethod
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
                ", isPayed=" + isPayed +
                ", paymentMethod=" + paymentMethod +
                '}';
    }
}
