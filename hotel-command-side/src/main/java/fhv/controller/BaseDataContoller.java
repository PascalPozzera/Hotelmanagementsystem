package fhv.controller;

import fhv.commands.admin.BaseDataService;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/admin")
public class BaseDataContoller {

    @Inject
    BaseDataService baseDataService;

    @POST
    @Path("loadBaseData")
    public Response loadBaseData() {
        baseDataService.loadBaseData();
        return Response.ok("Base data initialized.").build();
    }
}
