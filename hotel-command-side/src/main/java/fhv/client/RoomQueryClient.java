package fhv.client;

import at.fhv.sys.hotel.commands.shared.dto.booking.BookingAvailabilityResponseDTO;
import at.fhv.sys.hotel.commands.shared.dto.booking.BookingRequestDTO;
import at.fhv.sys.hotel.commands.shared.dto.customer.CustomerRequestDTO;
import at.fhv.sys.hotel.commands.shared.dto.room.RoomRequestDTO;
import at.fhv.sys.hotel.commands.shared.dto.room.RoomResponseDTO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "hotel-query-api-client")
@Path("/api")
public interface RoomQueryClient {

    @GET
    @Path("/{roomNumber}/exists")
    @Consumes(MediaType.APPLICATION_JSON)
    RoomResponseDTO doesRoomExist(@PathParam("roomNumber") int roomNumber);

    @GET
    @Path("/validateDate")
    @Consumes(MediaType.APPLICATION_JSON)
    BookingAvailabilityResponseDTO isBookingDateValide(@BeanParam BookingRequestDTO bookingRequestDTO);

    @GET
    @Path("/getRoom")
    @Consumes(MediaType.APPLICATION_JSON)
    RoomResponseDTO getRoom(@BeanParam RoomRequestDTO roomRequestDTO);
}
