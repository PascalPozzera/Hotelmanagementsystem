package fhv.controller;

import fhv.commands.customer.CreateCustomerCommand;
import fhv.commands.customer.CustomerAggregate;
import fhv.dto.requestDTO.customer.CustomerRequestDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.UUID;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerCommandController {

    @Inject
    CustomerAggregate customerAggregate;

    @POST
    @Path("/createCustomer")
    public String createCustomer(@BeanParam CustomerRequestDTO requestDTO) {
        String generatedId = UUID.randomUUID().toString();
        return customerAggregate.handle(new CreateCustomerCommand(generatedId, requestDTO.getFirstName(), requestDTO.getLastname(), requestDTO.getEmail()));
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
