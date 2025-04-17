package fhv.service.booking;


import fhv.models.booking.BookingQueryPanacheModel;
import io.quarkus.hibernate.orm.panache.Panache;
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

    @Transactional
    public void updateBooking(BookingQueryPanacheModel panacheModel) {
        BookingQueryPanacheModel existing = getBookingById(panacheModel.bookingId);

        if (existing != null) {
            existing.paymentMethod = panacheModel.paymentMethod;
            existing.isPayed = panacheModel.isPayed;
            Panache.getEntityManager().flush();
        }
    }
}
