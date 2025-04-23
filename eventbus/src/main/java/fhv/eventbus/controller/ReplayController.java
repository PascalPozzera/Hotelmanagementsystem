package fhv.eventbus.controller;

import fhv.eventbus.services.ReplayService;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/admin")
public class ReplayController {

    @Inject
    ReplayService replayService;

    @POST
    @Path("/replayEvents")
    public Response replayAll() {
        return replayService.replayAllEvents();
    }
}

