package fhv.service.booking;


import fhv.models.booking.BookingQueryPanacheModel;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class BookingServicePanache {

    public List<BookingQueryPanacheModel> getAllBookings() {
        return BookingQueryPanacheModel.listAll();
    }

    public BookingQueryPanacheModel getBookingById(UUID id) {
        return BookingQueryPanacheModel.findById(id);
    }

    @Transactional
    public void createBooking(BookingQueryPanacheModel booking) {
        booking.persist();
    }

    @Transactional
    public void cancelBooking(BookingQueryPanacheModel booking) {
        booking.delete();
    }
}
