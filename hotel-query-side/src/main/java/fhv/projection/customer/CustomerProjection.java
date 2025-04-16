package fhv.projection.customer;

import at.fhv.sys.hotel.commands.shared.dto.customer.CustomerResponseDTO;
import at.fhv.sys.hotel.commands.shared.events.CustomerCreated;
import fhv.models.customer.CustomerQueryPanacheModel;
import fhv.service.customer.CustomerServicePanache;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class CustomerProjection {

    @Inject
    CustomerServicePanache customerServicePanache;

    public List<CustomerResponseDTO> getAllCustomers() {

        List<CustomerQueryPanacheModel> bookings = customerServicePanache.getAllCustomers();

        return bookings.stream()
                .map(CustomerQueryPanacheModel::toDTO)
                .toList();
    }

    public void processCustomerCreatedEvent(CustomerCreated customerCreatedEvent) {
        Logger.getAnonymousLogger().info("Processing event: " + customerCreatedEvent);

        CustomerQueryPanacheModel customer = new CustomerQueryPanacheModel(
                customerCreatedEvent.getFirstName(),
                customerCreatedEvent.getLastName(),
                customerCreatedEvent.getEmail());

        customerServicePanache.createCustomer(customer);
    }
}
