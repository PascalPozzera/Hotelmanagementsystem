package fhv.client;

import at.fhv.sys.hotel.commands.shared.dto.booking.BookingAvailabilityResponseDTO;
import at.fhv.sys.hotel.commands.shared.dto.booking.BookingRequestDTO;
import at.fhv.sys.hotel.commands.shared.dto.customer.CustomerEmailExistsResponseDTO;
import at.fhv.sys.hotel.commands.shared.dto.customer.CustomerRequestDTO;
import at.fhv.sys.hotel.commands.shared.dto.room.RoomResponseDTO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "hotel-query-api-client")
@Path("/api")
public interface CustomerQueryClient {

    @GET
    @Path("/emailExists")
    @Consumes(MediaType.APPLICATION_JSON)
    CustomerRequestDTO doesEmailExist(@BeanParam CustomerRequestDTO customerRequestDTO);
}
