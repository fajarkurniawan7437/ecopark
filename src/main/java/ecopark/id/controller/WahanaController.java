package ecopark.id.controller;

import ecopark.id.model.Wahana;
import ecopark.id.service.WahanaService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

@Path("/wahana")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WahanaController {
    @Inject
    WahanaService wahanaService;

    @GET
    public Response get(){
        return wahanaService.get();
    }
    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") Long id) {
        return wahanaService.get(id);
    }

    @POST
    public Response post(Map<String, Object> request){
        return wahanaService.post(request);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response put(@PathParam("id") Long id, Map<String, Object> request){
        return wahanaService.put(id, request);
    }
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        return wahanaService.delete(id);
    }

}
