package fhv.commands.booking;

import at.fhv.sys.hotel.commands.shared.dto.booking.BookingAvailabilityResponseDTO;
import at.fhv.sys.hotel.commands.shared.dto.booking.BookingRequestDTO;
import at.fhv.sys.hotel.commands.shared.dto.customer.CustomerRequestDTO;
import at.fhv.sys.hotel.commands.shared.dto.room.RoomResponseDTO;
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
}

