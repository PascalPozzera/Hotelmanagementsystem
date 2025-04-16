package fhv.projection.booking;

import at.fhv.sys.hotel.commands.shared.dto.BookingResponseDTO;
import at.fhv.sys.hotel.commands.shared.events.BookingCreated;
import fhv.models.booking.BookingQueryPanacheModel;
import fhv.service.booking.BookingServicePanache;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

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
