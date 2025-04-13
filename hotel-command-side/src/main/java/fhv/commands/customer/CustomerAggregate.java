package fhv.commands.customer;

import at.fhv.sys.hotel.commands.shared.events.CustomerCreated;
import fhv.client.EventBusClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.logging.Logger;

@ApplicationScoped
public class CustomerAggregate {

    @Inject
    @RestClient
    EventBusClient eventClient;

    public String handle(CreateCustomerCommand command) {
        CustomerCreated event = new CustomerCreated(command.customerId(), command.firstName(), command.lastName(), command.email());

        Logger.getAnonymousLogger().info(eventClient.processCustomerCreatedEvent(event).toString());

        return command.customerId();
    }

}
