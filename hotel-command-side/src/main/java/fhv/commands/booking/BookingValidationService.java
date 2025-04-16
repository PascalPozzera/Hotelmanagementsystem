package fhv.commands.booking;

import at.fhv.sys.hotel.commands.shared.dto.booking.BookingAvailabilityResponseDTO;
import at.fhv.sys.hotel.commands.shared.dto.booking.BookingRequestDTO;
import at.fhv.sys.hotel.commands.shared.dto.room.RoomResponseDTO;
import fhv.client.RoomQueryClient;
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

    public List<String> validateCreateBookingCommand(CreateBookingCommand command) {
        List<String> errors = new ArrayList<>();

        if (command.roomNumber() <= 0) {
            errors.add("Room number must not be 0 or less.");
        }

        if (command.customerId() <= 0) {
            errors.add("Customer ID must not be 0 or less.");
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
            log.error(e.getMessage(), e);
            return false;
        }
    }

    public boolean isRoomAvailable(CreateBookingCommand command) {
        try {
            BookingRequestDTO bookingRequestDTO = new BookingRequestDTO();
            bookingRequestDTO.setRoomNumber(command.roomNumber());
            bookingRequestDTO.setCustomerId(command.customerId());
            bookingRequestDTO.setStartDate(command.startDate());
            bookingRequestDTO.setEndDate(command.endDate());
            bookingRequestDTO.setNumberOfGuests(command.numberOfGuests());

            BookingAvailabilityResponseDTO response = roomQueryClient.isBookingDateValide(bookingRequestDTO);

            return response != null && response.available();
        } catch (Exception e) {
            log.error("Error while checking room availability", e);
            return false;
        }
    }
}

