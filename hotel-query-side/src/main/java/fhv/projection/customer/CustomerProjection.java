package fhv.projection.customer;

import at.fhv.sys.hotel.commands.shared.events.CustomerCreated;
import fhv.models.customer.CustomerQueryModel;
import fhv.models.customer.CustomerQueryPanacheModel;
import fhv.service.customer.CustomerService;
import fhv.service.customer.CustomerServicePanache;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.logging.Logger;

@ApplicationScoped
public class CustomerProjection {

    @Inject
    CustomerService customerService;

    @Inject
    CustomerServicePanache customerServicePanache;

    public void processCustomerCreatedEvent(CustomerCreated customerCreatedEvent) {
        Logger.getAnonymousLogger().info("Processing event: " + customerCreatedEvent);
        customerService.createCustomer(new CustomerQueryModel(customerCreatedEvent.getUserId(), customerCreatedEvent.getEmail()));

        CustomerQueryPanacheModel customer = new CustomerQueryPanacheModel();
        customer.userId = customerCreatedEvent.getUserId();
        customer.email = customerCreatedEvent.getEmail();
        customerServicePanache.createCustomer(customer);

    }
}
