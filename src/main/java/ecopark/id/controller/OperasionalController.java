package ecopark.id.controller;

import ecopark.id.service.OperasionalService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

@Path("/operasional")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OperasionalController {
    @Inject
    OperasionalService operasionalService;

    @GET
    public Response get(){
        return operasionalService.get();
    }
    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") Long id) {
        return operasionalService.get(id);
    }

    @POST
    public Response post(Map<String, Object> request){
        return operasionalService.post(request);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response put(@PathParam("id") Long id, Map<String, Object> request){
        return operasionalService.put(id, request);
    }
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        return operasionalService.delete(id);
    }
}
