package fhv.controller;

import at.fhv.sys.hotel.commands.shared.dto.customer.CustomerRequestDTO;
import fhv.commands.customer.CreateCustomerCommand;
import fhv.commands.customer.CustomerAggregate;
import fhv.commands.customer.UpdateCustomerCommand;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerCommandController {

    @Inject
    CustomerAggregate customerAggregate;

    @POST
    @Path("/createCustomer")
    public String createCustomer(@BeanParam CustomerRequestDTO requestDTO) {
        return customerAggregate.handle(new CreateCustomerCommand(requestDTO.getFirstName(), requestDTO.getLastname(), requestDTO.getEmail(), requestDTO.getAddress(), requestDTO.getBirthDate()));
    }

    @POST
    @Path("/update")
    public String updateCustomer(@BeanParam CustomerRequestDTO requestDTO) {
        return customerAggregate.handle(new UpdateCustomerCommand(requestDTO.getFirstName(), requestDTO.getLastname(), requestDTO.getEmail(), requestDTO.getAddress(), requestDTO.getBirthDate()));
    }
}
