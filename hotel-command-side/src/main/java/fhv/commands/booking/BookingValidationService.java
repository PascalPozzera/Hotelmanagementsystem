package fhv.commands.booking;

import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class BookingValidationService {

    public List<String> validateCreateBooking(CreateBookingCommand command) {
        List<String> errors = new ArrayList<>();

        if (command.bookingId() == null || command.bookingId().isEmpty()) {
            errors.add("Booking ID must not be empty.");
        }

        if (command.roomNumber() == null || command.roomNumber().isEmpty()) {
            errors.add("Room number must not be empty.");
        }

        if (command.customerId() == null || command.customerId().isEmpty()) {
            errors.add("Customer ID must not be empty.");
        }

        if (command.startDate() == null) {
            errors.add("Start date must not be null.");
        }

        if (command.endDate() == null) {
            errors.add("End date must not be null.");
        }

        if (command.startDate() != null && command.endDate() != null) {
            if (command.startDate().isAfter(command.endDate())) {
                errors.add("Start date must be before end date.");
            }

            if (command.startDate().isBefore(LocalDate.now())) {
                errors.add("Start date must not be in the past.");
            }
        }

        if (command.numberOfGuests() <= 0) {
            errors.add("Number of guests must be greater than 0.");
        }

        return errors;
    }
}

