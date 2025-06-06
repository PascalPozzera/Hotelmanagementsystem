package fhv.eventbus.controller;

import at.fhv.sys.hotel.commands.shared.events.booking.BookingCancelled;
import at.fhv.sys.hotel.commands.shared.events.booking.BookingCreated;
import at.fhv.sys.hotel.commands.shared.events.booking.BookingPayed;
import at.fhv.sys.hotel.commands.shared.events.customer.*;
import at.fhv.sys.hotel.commands.shared.events.RoomCreated;
import fhv.eventbus.services.EventProcessingService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.jboss.logmanager.Logger;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EventsController {
    @Inject
    EventProcessingService eventStoreService;

    public EventsController() {
    }

    @POST
    @Operation(hidden = true) //The POST method is hidden to prevent it from being visible to the user in Swagger.
    @Path("/customerCreated")
    public Response customerCreated(CustomerCreated event) {
        Logger.getAnonymousLogger().info("Received event: " + event);
        eventStoreService.processEvent("customer-email: " + event.getEmail(), event);
        return Response.ok(event).build();
    }

    @POST
    @Operation(hidden = true) //The POST method is hidden to prevent it from being visible to the user in Swagger.
    @Path("/customerUpdated")
    public Response customerUpdated(CustomerUpdated event) {
        Logger.getAnonymousLogger().info("Received event: " + event);
        eventStoreService.processEvent("customer-email: " + event.getEmail(), event);
        return Response.ok(event).build();
    }

    @POST
    @Operation(hidden = true) //The POST method is hidden to prevent it from being visible to the user in Swagger.
    @Path("/bookingCreated")
    public Response bookingCreated(BookingCreated event) {
        Logger.getAnonymousLogger().info("Received event: " + event);
        eventStoreService.processEvent("booking-RoomNumber: " + event.getRoomNumber(), event);
        return Response.ok(event).build();
    }

    @POST
    @Operation(hidden = true) //The POST method is hidden to prevent it from being visible to the user in Swagger.
    @Path("/bookingCancelled")
    public Response bookingCancelled(BookingCancelled event) {
        Logger.getAnonymousLogger().info("Received event: " + event);
        eventStoreService.processEvent("booking-RoomNumber: " + event.getRoomNumber(), event);
        return Response.ok(event).build();
    }

    @POST
    @Operation(hidden = true) //The POST method is hidden to prevent it from being visible to the user in Swagger.
    @Path("/bookingPayed")
    public Response bookingPayed(BookingPayed event) {
        Logger.getAnonymousLogger().info("Received event: " + event);
        eventStoreService.processEvent("booking-RoomNumber: " + event.getRoomNumber(), event);
        return Response.ok(event).build();
    }

    @POST
    @Operation(hidden = true) //The POST method is hidden to prevent it from being visible to the user in Swagger.
    @Path("/roomCreated")
    public Response RoomCreated(RoomCreated event) {
        Logger.getAnonymousLogger().info("Received event: " + event);
        eventStoreService.processEvent("room-" + event.getRoomId(), event);
        return Response.ok(event).build();
    }
}
