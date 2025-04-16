package fhv.service.customer;


import fhv.models.customer.CustomerQueryPanacheModel;
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

    @Transactional
    public void createCustomer(CustomerQueryPanacheModel customer) {
        customer.persist();
    }
}
