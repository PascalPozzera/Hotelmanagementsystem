package fhv.service.customer;


import fhv.models.customer.CustomerQueryPanacheModel;
import io.quarkus.hibernate.orm.panache.Panache;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class CustomerServicePanache {

    public List<CustomerQueryPanacheModel> getAllCustomers() {
        return CustomerQueryPanacheModel.listAll();
    }

    public CustomerQueryPanacheModel getCustomerByEmail(String email) {
        return CustomerQueryPanacheModel.find("email", email).firstResult();
    }

    public List<CustomerQueryPanacheModel> getCustomersByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return CustomerQueryPanacheModel.listAll();
        }

        return CustomerQueryPanacheModel.find(
                "lower(firstName) like ?1 or lower(lastName) like ?1",
                "%" + name.toLowerCase() + "%"
        ).list();
    }

    @Transactional
    public void createCustomer(CustomerQueryPanacheModel customer) {
        customer.persist();
    }

    @Transactional
    public void updateCustomer(CustomerQueryPanacheModel updatedCustomer) {
        CustomerQueryPanacheModel existing = getCustomerByEmail(updatedCustomer.email);

        if (existing != null) {
            existing.firstName = updatedCustomer.firstName;
            existing.lastName = updatedCustomer.lastName;
            Panache.getEntityManager().flush();
        }
    }
}
