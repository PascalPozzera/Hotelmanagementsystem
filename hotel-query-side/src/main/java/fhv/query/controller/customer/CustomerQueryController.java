package fhv.query.controller.customer;

import at.fhv.sys.hotel.commands.shared.dto.customer.CustomerRequestDTO;
import at.fhv.sys.hotel.commands.shared.dto.customer.CustomerResponseDTO;
import at.fhv.sys.hotel.commands.shared.events.customer.CustomerCreated;
import at.fhv.sys.hotel.commands.shared.events.customer.CustomerUpdated;
import fhv.projection.customer.CustomerProjection;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.jboss.logmanager.Logger;

import java.util.List;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerQueryController {

    @Inject
    CustomerProjection customerProjection;

    @GET
    @Path("/getCustomers")
    public Response getCustomers() {
        List<CustomerResponseDTO> customers = customerProjection.getAllCustomers();
        return Response.ok(customers).build();
    }

    @GET
    @Operation(hidden = true) //The method is hidden to prevent it from being visible to the user in Swagger.
    @Path("/emailExists")
    public Response checkEmailExist(@BeanParam CustomerRequestDTO customerRequestDTO) {
        CustomerResponseDTO customer = customerProjection.getCustomerByEmail(customerRequestDTO);
        return Response.ok(customer).build();
    }

    @POST
    @Operation(hidden = true) //The method is hidden to prevent it from being visible to the user in Swagger.
    @Path("/customerCreated")
    public Response customerCreated(CustomerCreated event) {
        Logger.getAnonymousLogger().info("Received event: " + event);
        customerProjection.processCustomerCreatedEvent(event);
        return Response.ok(event).build();
    }

    @POST
    @Operation(hidden = true) //The method is hidden to prevent it from being visible to the user in Swagger.
    @Path("/customerUpdated")
    public Response customerUpdated(CustomerUpdated event) {
        Logger.getAnonymousLogger().info("Received event: " + event);
        customerProjection.processCustomerUpdatedEvent(event);
        return Response.ok(event).build();
    }
}
