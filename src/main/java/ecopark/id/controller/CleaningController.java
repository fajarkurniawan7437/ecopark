package ecopark.id.controller;

import ecopark.id.model.Cleaning;
import ecopark.id.service.CleaningService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

@Path("/cleaning")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CleaningController {
    @Inject
    CleaningService cleaningService;

    @GET
    public Response get(){
        return cleaningService.get();
    }
    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") Long id) {
        return cleaningService.get(id);
    }

    @POST
    public Response post(Map<String, Object> request){
        return cleaningService.post(request);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response put(@PathParam("id") Long id, Map<String, Object> request){
        return cleaningService.put(id, request);
    }
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        return cleaningService.delete(id);
    }
}
