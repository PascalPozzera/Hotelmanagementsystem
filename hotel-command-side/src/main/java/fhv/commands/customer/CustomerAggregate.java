package fhv.commands.customer;

import at.fhv.sys.hotel.commands.shared.events.CustomerCreated;
import fhv.client.EventBusClient;
import fhv.commands.booking.BookingValidationService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@ApplicationScoped
public class CustomerAggregate {

    @Inject
    @RestClient
    EventBusClient eventClient;

    @Inject
    CustomerValidationService customerValidationService;

    public String handle(CreateCustomerCommand command) {

        List<String> errors = customerValidationService.validateCreateCustomerCommand(command);

        if (!errors.isEmpty()) {
            throw new IllegalArgumentException("Customer validation failed: " + String.join("; ", errors));
        }

        if(customerValidationService.validateEmail(command.email())){
            throw new IllegalArgumentException("Email: " + command.email() + " already exist.");
        }

        CustomerCreated event = new CustomerCreated(UUID.randomUUID(), command.firstName(), command.lastName(), command.email());

        Logger.getAnonymousLogger().info(eventClient.processCustomerCreatedEvent(event).toString());

        return command.firstName() + " " + command.lastName();
    }

}
