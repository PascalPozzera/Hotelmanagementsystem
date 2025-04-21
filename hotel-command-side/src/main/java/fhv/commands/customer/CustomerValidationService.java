package fhv.commands.customer;

import at.fhv.sys.hotel.commands.shared.dto.booking.BookingAvailabilityResponseDTO;
import at.fhv.sys.hotel.commands.shared.dto.booking.BookingRequestDTO;
import at.fhv.sys.hotel.commands.shared.dto.customer.CustomerRequestDTO;
import at.fhv.sys.hotel.commands.shared.dto.room.RoomResponseDTO;
import fhv.client.CustomerQueryClient;
import fhv.client.RoomQueryClient;
import fhv.commands.booking.CreateBookingCommand;
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
public class CustomerValidationService {

    @Inject
    @RestClient
    CustomerQueryClient customerQueryClient;

    public List<String> validateCreateCustomerCommand(CreateCustomerCommand command) {
        return checkCommands(command.firstName(), command.lastName(), command.email(), command.address(), command.birthDate());
    }

    public List<String> validateUpdateCustomerCommand(UpdateCustomerCommand command) {
        return checkCommands(command.firstName(), command.lastName(), command.email(), command.address(), command.birthDate());
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

    private List<String> checkCommands(String firstName, String lastName, String email, String address, LocalDate birthDate) {

        List<String> errors = new ArrayList<>();

        if (firstName == null || firstName.isBlank()) {
            errors.add("First name must not be null or empty.");
        }

        if (lastName == null || lastName.isBlank()) {
            errors.add("Last name must not be null or empty.");
        }

        if (email == null || email.isBlank()) {
            errors.add("Email must not be null or empty.");
        } else if (!email.contains("@")) {
            errors.add("Email must be a valid address.");
        }

        if (address == null || address.isBlank()) {
            errors.add("Address must not be null or empty.");
        }

        if (birthDate == null) {
            errors.add("Birth date must not be null.");
        }

        return errors;

    }
}

