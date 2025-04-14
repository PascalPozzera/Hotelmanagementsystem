package fhv.projection.booking;

import at.fhv.sys.hotel.commands.shared.events.BookingCreated;
import at.fhv.sys.hotel.commands.shared.events.CustomerCreated;
import fhv.models.booking.BookingQueryModel;
import fhv.models.booking.BookingQueryPanacheModel;
import fhv.models.customer.CustomerQueryModel;
import fhv.models.customer.CustomerQueryPanacheModel;
import fhv.service.booking.BookingService;
import fhv.service.booking.BookingServicePanache;
import fhv.service.customer.CustomerService;
import fhv.service.customer.CustomerServicePanache;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.logging.Logger;

@ApplicationScoped
public class BookingProjection {

    @Inject
    CustomerService customerService;
    @Inject
    BookingService bookingService;

    @Inject
    CustomerServicePanache customerServicePanache;
    @Inject
    BookingServicePanache bookingServicePanache;


    public void processCustomerCreatedEvent(CustomerCreated customerCreatedEvent) {
        Logger.getAnonymousLogger().info("Processing event: " + customerCreatedEvent);

        customerService.createCustomer(new CustomerQueryModel(
                customerCreatedEvent.getUserId(),
                customerCreatedEvent.getFirstName(),
                customerCreatedEvent.getLastName(),
                customerCreatedEvent.getEmail()));

        CustomerQueryPanacheModel customer = new CustomerQueryPanacheModel(
                customerCreatedEvent.getUserId(),
                customerCreatedEvent.getFirstName(),
                customerCreatedEvent.getLastName(),
                customerCreatedEvent.getEmail());

        customerServicePanache.createCustomer(customer);
    }

    public void processBookingCreatedEvent(BookingCreated bookingCreatedEvent) {
        Logger.getAnonymousLogger().info("Processing event: " + bookingCreatedEvent);

        BookingQueryModel model = new BookingQueryModel(
                bookingCreatedEvent.getBookingId(),
                bookingCreatedEvent.getRoomNumber(),
                bookingCreatedEvent.getCustomerId(),
                bookingCreatedEvent.getStartDate(),
                bookingCreatedEvent.getEndDate(),
                bookingCreatedEvent.getNumberOfGuests()
        );

        bookingService.createBooking(model);

        // Optional: auch für Panache-Modell, falls du das parallel nutzt
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
