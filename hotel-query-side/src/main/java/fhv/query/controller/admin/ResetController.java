package fhv.query.controller.admin;

import fhv.models.booking.BookingQueryPanacheModel;
import fhv.service.admin.ResetService;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/admin")
public class ResetController {

    @Inject
    ResetService resetService;

    @POST
    @Path("/reset")
    public Response resetAll() {
        resetService.resetQueryModels();
        return Response.ok("Query models cleared.").build();
    }
}
