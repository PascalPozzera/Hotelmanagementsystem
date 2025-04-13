package fhv.controller;

import fhv.commands.customer.CreateCustomerCommand;
import fhv.commands.customer.CustomerAggregate;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerCommandController {

    CustomerAggregate customerAggregate;

    public CustomerCommandController(CustomerAggregate customerAggregate) {
        this.customerAggregate = customerAggregate;
    }

    @POST
    @Path("/createCustomer")
    public String createCustomer(@QueryParam("customerId") String customerId, @QueryParam("name") String name, @QueryParam("email") String email) {
        return customerAggregate.handle(new CreateCustomerCommand(customerId, name, email));
    }

    @POST
    @Path("/{customerId}/update")
    public String updateCustomer(@PathParam("customerId") String customerId, @QueryParam("email") String email) {
        // TBD: process customer
        return "Customer updated";
    }

    @POST
    @Path("/{customerId}/delete")
    public String deleteCustomer(@PathParam("customerId") String customerId) {
        // TBD: delete customer
        return "Customer deleted";
    }
}
