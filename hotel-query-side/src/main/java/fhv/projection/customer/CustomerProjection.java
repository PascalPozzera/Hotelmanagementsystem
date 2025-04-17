package fhv.projection.customer;

import at.fhv.sys.hotel.commands.shared.dto.customer.CustomerRequestDTO;
import at.fhv.sys.hotel.commands.shared.dto.customer.CustomerResponseDTO;
import at.fhv.sys.hotel.commands.shared.events.customer.CustomerCreated;
import at.fhv.sys.hotel.commands.shared.events.customer.CustomerUpdated;
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

    public CustomerResponseDTO getCustomerByEmail(CustomerRequestDTO customerRequestDTO) {
        return customerServicePanache.getCustomerByEmail(customerRequestDTO.getEmail()).toDTO();
    }

    public List<CustomerResponseDTO> getCustomersByName(String name) {
        return customerServicePanache.getCustomersByName(name).stream().map(CustomerQueryPanacheModel::toDTO).toList();
    }

    public void processCustomerCreatedEvent(CustomerCreated customerCreatedEvent) {
        Logger.getAnonymousLogger().info("Processing event: " + customerCreatedEvent);

        CustomerQueryPanacheModel customer = new CustomerQueryPanacheModel(
                customerCreatedEvent.getCustomerId(),
                customerCreatedEvent.getFirstName(),
                customerCreatedEvent.getLastName(),
                customerCreatedEvent.getEmail());

        customerServicePanache.createCustomer(customer);
    }

    public void processCustomerUpdatedEvent(CustomerUpdated customerUpdatedEvent) {
        Logger.getAnonymousLogger().info("Processing event: " + customerUpdatedEvent);

        CustomerQueryPanacheModel customer = new CustomerQueryPanacheModel(
                customerUpdatedEvent.getFirstName(),
                customerUpdatedEvent.getLastName(),
                customerUpdatedEvent.getEmail());

        customerServicePanache.updateCustomer(customer);
    }
}
