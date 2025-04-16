package fhv.query.controller.room;

import at.fhv.sys.hotel.commands.shared.dto.booking.BookingAvailabilityResponseDTO;
import at.fhv.sys.hotel.commands.shared.dto.booking.BookingRequestDTO;
import at.fhv.sys.hotel.commands.shared.dto.room.RoomResponseDTO;
import at.fhv.sys.hotel.commands.shared.events.RoomCreated;
import fhv.projection.room.RoomProjection;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.jboss.logmanager.Logger;

import java.time.LocalDate;
import java.util.List;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RoomQueryController {

    @Inject
    RoomProjection roomProjection;

    @GET
    @Path("/getFreeRooms")
    public Response getFreeRooms(@QueryParam("startDate") LocalDate startDate,
                                 @QueryParam("endDate") LocalDate endDate) {

        if (startDate == null || endDate == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("startDate and endDate query parameters are required (format: yyyy-MM-dd)").build();
        }

        if (startDate.isAfter(endDate)) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("The start date must no be after the end date").build();
        }

        List<RoomResponseDTO> rooms = roomProjection.getAvailableRooms(startDate, endDate);
        return Response.ok(rooms).build();
    }

    @GET
    @Path("/{roomNumber}/exists")
    public RoomResponseDTO doesRoomExist(@PathParam("roomNumber") String roomNumber) {
        return roomProjection.getRoomById(roomNumber);
    }

    @GET
    @Path("/validateDate")
    public BookingAvailabilityResponseDTO isBookingDateValide(@BeanParam BookingRequestDTO request) {
        boolean available = roomProjection.isRoomAvailable(
                request.getRoomNumber(),
                request.getStartDate(),
                request.getEndDate()
        );
        return new BookingAvailabilityResponseDTO(available);
    }


    @POST
    @Operation(hidden = true) //The POST method is hidden to prevent it from being visible to the user in Swagger.
    @Path("/roomCreated")
    public Response roomCreated(RoomCreated event) {
        Logger.getAnonymousLogger().info("Received event: " + event);
        roomProjection.processRoomCreatedEvent(event);
        return Response.ok(event).build();
    }
}
