package fhv.commands.booking;

import at.fhv.sys.hotel.commands.shared.events.BookingCreated;
import fhv.client.EventBusClient;
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

        List<String> errors = bookingValidationService.validateCreateBookingCommand(command);
        if (!errors.isEmpty()) {
            throw new IllegalArgumentException("Booking validation failed: " + String.join("; ", errors));
        }

        if(!bookingValidationService.validateEmail(command.email())){
            throw new IllegalArgumentException("Email: " + command.email() + " does not exist.");
        }

        if(!bookingValidationService.validateRoomToBookExists(command.roomNumber())){
            throw new IllegalArgumentException("Room with number : " + command.roomNumber() + " does not exist.");
        }

        if(!bookingValidationService.isRoomAvailable(command)){
            throw new IllegalArgumentException("Booking not allowed for room " + command.roomNumber());
        }

        BookingCreated event = new BookingCreated(
                command.roomNumber(),
                command.email(),
                command.startDate(),
                command.endDate(),
                false,
                command.numberOfGuests());

        Logger.getAnonymousLogger().info(eventClient.processBookingCreatedEvent(event).toString());

        return command.toString();
    }
}
