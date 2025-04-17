package fhv.commands.booking;

import at.fhv.sys.hotel.commands.shared.events.booking.BookingCancelled;
import at.fhv.sys.hotel.commands.shared.events.booking.BookingCreated;
import at.fhv.sys.hotel.commands.shared.events.booking.BookingPayed;
import fhv.client.EventBusClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;
import java.util.UUID;
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

        if (!bookingValidationService.validateRoomToBookExists(command.roomNumber())) {
            throw new IllegalArgumentException("Room with number : " + command.roomNumber() + " does not exist.");
        }

        if (!bookingValidationService.isRoomAvailable(command)) {
            throw new IllegalArgumentException("Room " + command.roomNumber() + " cannot be booked as it is currently not available ");
        }

        if (!bookingValidationService.validateEmail(command.email())) {
            throw new IllegalArgumentException("Email: " + command.email() + " does not exist.");
        }

        if (!bookingValidationService.validateGuestCountForRoom(command)) {
            throw new IllegalArgumentException("The room " + command.roomNumber() + " does not have the required number of guests");
        }

        BookingCreated event = new BookingCreated(
                UUID.randomUUID(),
                command.roomNumber(),
                command.email(),
                command.startDate(),
                command.endDate(),
                false,
                command.numberOfGuests());

        Logger.getAnonymousLogger().info(eventClient.processBookingCreatedEvent(event).toString());
        return command.toString();
    }

    public String handle(PayBookingCommand command) {

        List<String> errors = bookingValidationService.validatePayBookingCommand(command);
        if (!errors.isEmpty()) {
            throw new IllegalArgumentException("Pay booking validation failed: " + String.join("; ", errors));
        }

        if (!bookingValidationService.validateEmail(command.email())) {
            throw new IllegalArgumentException("Email: " + command.email() + " does not exist.");
        }

        BookingPayed event = new BookingPayed(command.bookingId(), command.email(), command.roomNumber(), true, command.paymentMethod());
        Logger.getAnonymousLogger().info(eventClient.processBookingPayedEvent(event).toString());

        return "Booking payed: " + command.bookingId();
    }

    public String handle(CancelBookingCommand command) {

        List<String> errors = bookingValidationService.validateCancelBookingCommand(command);
        if (!errors.isEmpty()) {
            throw new IllegalArgumentException("Cancel validation failed: " + String.join("; ", errors));
        }

        if (!bookingValidationService.validateEmail(command.email())) {
            throw new IllegalArgumentException("Email: " + command.email() + " does not exist.");
        }

        BookingCancelled event = new BookingCancelled(command.bookingId(), command.email(), command.roomNumber());
        Logger.getAnonymousLogger().info(eventClient.processBookingCancelledEvent(event).toString());

        return "Booking cancelled: " + command.bookingId();
    }
}
