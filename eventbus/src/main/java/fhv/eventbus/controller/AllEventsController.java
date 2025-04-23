package fhv.eventbus.controller;

import fhv.eventbus.dto.StoredEventDTO;
import fhv.eventbus.services.AllEventsService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/admin")
public class AllEventsController {

    @Inject
    AllEventsService allEventsService;

    @GET
    @Path("/allEvents")
    @Produces(MediaType.APPLICATION_JSON)
    public List<StoredEventDTO> getAllEvents() {
        return allEventsService.getAllEvents();
    }
}
