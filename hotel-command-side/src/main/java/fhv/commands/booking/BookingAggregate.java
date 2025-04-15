package fhv.commands.booking;

import at.fhv.sys.hotel.commands.shared.events.BookingCreated;
import at.fhv.sys.hotel.commands.shared.events.CustomerCreated;
import fhv.client.EventBusClient;
import fhv.commands.customer.CreateCustomerCommand;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class BookingAggregate {

    @Inject
    @RestClient
    EventBusClient eventClient;

    @Inject
    BookingValidationService bookingValidationService;

    public String handle(CreateBookingCommand command) {
        List<String> errors = bookingValidationService.validateCreateBooking(command);
        if (!errors.isEmpty()) {
            throw new IllegalArgumentException("Booking validation failed: " + String.join("; ", errors));
        }

        BookingCreated event = new BookingCreated(
                command.bookingId(),
                command.roomNumber(),
                command.customerId(),
                command.startDate(),
                command.endDate(),
                false,
                command.numberOfGuests());

        Logger.getAnonymousLogger().info(eventClient.processBookingCreatedEvent(event).toString());

        return command.bookingId();
    }
}
