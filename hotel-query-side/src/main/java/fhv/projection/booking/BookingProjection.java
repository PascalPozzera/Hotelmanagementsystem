package fhv.projection.booking;

import at.fhv.sys.hotel.commands.shared.dto.booking.BookingResponseDTO;
import at.fhv.sys.hotel.commands.shared.events.BookingCreated;
import fhv.models.booking.BookingQueryPanacheModel;
import fhv.service.booking.BookingServicePanache;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class BookingProjection {

    @Inject
    BookingServicePanache bookingServicePanache;

    public List<BookingResponseDTO> getAllBookings() {

        List<BookingQueryPanacheModel> bookings = bookingServicePanache.getAllBookings();

        return bookings.stream()
                .map(BookingQueryPanacheModel::toDTO)
                .toList();
    }

    public static boolean isRoomAvailable(String roomId, LocalDate startDate, LocalDate endDate) {
        long count = BookingQueryPanacheModel.find(
                "roomId = ?1 AND startDate <= ?2 AND endDate >= ?3",
                roomId, endDate, startDate
        ).count();

        return count == 0;
    }

    public void processBookingCreatedEvent(BookingCreated bookingCreatedEvent) {

        Logger.getAnonymousLogger().info("Processing event: " + bookingCreatedEvent);

        BookingQueryPanacheModel panacheModel = new BookingQueryPanacheModel(
                bookingCreatedEvent.getBookingId(),
                bookingCreatedEvent.getRoomNumber(),
                bookingCreatedEvent.getCustomerId(),
                bookingCreatedEvent.getStartDate(),
                bookingCreatedEvent.getEndDate(),
                bookingCreatedEvent.getNumberOfGuests()
        );

        bookingServicePanache.createBooking(panacheModel);
    }
}
