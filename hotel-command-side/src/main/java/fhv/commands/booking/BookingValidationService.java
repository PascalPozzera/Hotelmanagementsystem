package fhv.commands.booking;

import at.fhv.sys.hotel.commands.shared.dto.booking.BookingAvailabilityResponseDTO;
import at.fhv.sys.hotel.commands.shared.dto.booking.BookingRequestDTO;
import at.fhv.sys.hotel.commands.shared.dto.customer.CustomerRequestDTO;
import at.fhv.sys.hotel.commands.shared.dto.room.RoomRequestDTO;
import at.fhv.sys.hotel.commands.shared.dto.room.RoomResponseDTO;
import at.fhv.sys.hotel.commands.shared.enums.PaymentMethod;
import fhv.client.CustomerQueryClient;
import fhv.client.RoomQueryClient;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@ApplicationScoped
public class BookingValidationService {

    @Inject
    @RestClient
    RoomQueryClient roomQueryClient;

    @Inject
    @RestClient
    CustomerQueryClient customerQueryClient;

    public List<String> validateCreateBookingCommand(CreateBookingCommand command) {
        List<String> errors = new ArrayList<>();

        if (command.roomNumber() <= 0) {
            errors.add("Room number must not be 0 or less.");
        }

        if (command.email() == null || command.email().isEmpty()) {
            errors.add("Customer Email must not be empty or null.");
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

    public boolean validateRoomToBookExists(int roomNumber) {
        try {
            RoomResponseDTO room = roomQueryClient.doesRoomExist(roomNumber);
            return room != null;
        } catch (NotFoundException e) {
            return false;
        } catch (Exception e) {
            Log.error(e.getMessage(), e);
            return false;
        }
    }

    public boolean isRoomAvailable(CreateBookingCommand command) {
        try {
            BookingRequestDTO bookingRequestDTO = new BookingRequestDTO();
            bookingRequestDTO.setRoomNumber(command.roomNumber());
            bookingRequestDTO.setEmail(command.email());
            bookingRequestDTO.setStartDate(command.startDate());
            bookingRequestDTO.setEndDate(command.endDate());
            bookingRequestDTO.setNumberOfGuests(command.numberOfGuests());

            BookingAvailabilityResponseDTO response = roomQueryClient.isBookingDateValide(bookingRequestDTO);

            return response != null && response.available();
        } catch (Exception e) {
            Log.error("Error while checking room availability", e);
            return false;
        }
    }

    public boolean validateEmail(String email) {
        try {
            CustomerRequestDTO customerRequestDTO = new CustomerRequestDTO();
            customerRequestDTO.setEmail(email);

            CustomerRequestDTO response = customerQueryClient.doesEmailExist(customerRequestDTO);

            return response != null;

        } catch (Exception e) {
            Log.error("Error while checking email exists", e);
            return false;
        }
    }

    public boolean validateGuestCountForRoom(CreateBookingCommand command) {
        try {
            RoomRequestDTO roomRequestDTO = new RoomRequestDTO();
            roomRequestDTO.setRoomNumber(command.roomNumber());

            RoomResponseDTO response = roomQueryClient.getRoom(roomRequestDTO);

            return command.numberOfGuests() <= response.numberOfPerson();

        } catch (Exception e) {
            Log.error("Error while checking guest count", e);
            return false;
        }
    }

    public List<String> validateCancelBookingCommand(CancelBookingCommand command) {
        List<String> errors = new ArrayList<>();

        if (command.bookingId() == null) {
            errors.add("Booking ID must be a positive number.");
        }

        if (command.email() == null || command.email().isBlank()) {
            errors.add("Email must not be empty.");
        } else if (!command.email().matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            errors.add("Email format is invalid.");
        }

        if (command.roomNumber() <= 0) {
            errors.add("Room number must be a positive integer.");
        }

        return errors;
    }

    public List<String> validatePayBookingCommand(PayBookingCommand command) {

        List<String> errors = new ArrayList<>();

        if (command.bookingId() == null) {
            errors.add("Booking ID must be not null.");
        }

        if (command.email() == null || command.email().isBlank()) {
            errors.add("Email must not be empty.");
        } else if (!command.email().matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            errors.add("Email format is invalid.");
        }

        if (command.roomNumber() <= 0) {
            errors.add("Room number must be a positive number.");
        }

        if (command.paymentMethod() == null || command.paymentMethod() == PaymentMethod.NOT_SELECTED) {
            errors.add("Payment method must be selected.");
        }

        return errors;
    }
}

