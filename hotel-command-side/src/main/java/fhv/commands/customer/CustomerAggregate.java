package fhv.commands.customer;

import at.fhv.sys.hotel.commands.shared.events.customer.CustomerCreated;
import at.fhv.sys.hotel.commands.shared.events.customer.CustomerUpdated;
import fhv.client.CustomerQueryClient;
import fhv.client.EventBusClient;
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
    @RestClient
    CustomerQueryClient customerQueryClient;

    @Inject
    CustomerValidationService customerValidationService;

    public String handle(CreateCustomerCommand command) {

        List<String> errors = customerValidationService.validateCreateCustomerCommand(command);

        if (!errors.isEmpty()) {
            throw new IllegalArgumentException("Customer validation failed: " + String.join("; ", errors));
        }

        if (validateEmail(command.email())) {
            throw new IllegalArgumentException("Email: " + command.email() + " already exist.");
        }

        CustomerCreated event = new CustomerCreated(UUID.randomUUID(), command.firstName(), command.lastName(), command.email());

        Logger.getAnonymousLogger().info(eventClient.processCustomerCreatedEvent(event).toString());

        return command.firstName() + " " + command.lastName();
    }

    public String handle(UpdateCustomerCommand command) {

        List<String> errors = customerValidationService.validateUpdateCustomerCommand(command);

        if (!errors.isEmpty()) {
            throw new IllegalArgumentException("Update customer validation failed: " + String.join("; ", errors));
        }

        if (!validateEmail(command.email())) {
            throw new IllegalArgumentException("Email: " + command.email() + " does not exist.");
        }

        CustomerUpdated event = new CustomerUpdated(command.firstName(), command.lastName(), command.email());

        Logger.getAnonymousLogger().info(eventClient.processCustomerUpdatedEvent(event).toString());

        return command.firstName() + " " + command.lastName();
    }

    private boolean validateEmail(String email) {
        return customerValidationService.validateEmail(email);
    }

}
