package fhv.query.controller.room;

import at.fhv.sys.hotel.commands.shared.dto.RoomResponseDTO;
import at.fhv.sys.hotel.commands.shared.events.RoomCreated;
import fhv.projection.room.RoomProjection;
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
public class RoomQueryController {

    @Inject
    RoomProjection roomProjection;

    @GET
    @Path("/getRooms")
    public Response getRooms() {
        List<RoomResponseDTO> rooms = roomProjection.getAllRooms();
        return Response.ok(rooms).build();
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
