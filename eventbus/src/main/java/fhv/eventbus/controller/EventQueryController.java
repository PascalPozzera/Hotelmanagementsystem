package fhv.eventbus.controller;

import fhv.eventbus.dto.StoredEventDTO;
import fhv.eventbus.services.EventQueryService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/admin")
public class EventQueryController {

    @Inject
    EventQueryService eventQueryService;

    @GET
    @Path("/allEvents")
    @Produces(MediaType.APPLICATION_JSON)
    public List<StoredEventDTO> getAllEvents() {
        return eventQueryService.getAllEvents();
    }
}
