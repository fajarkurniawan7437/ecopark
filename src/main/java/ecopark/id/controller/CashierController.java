package ecopark.id.controller;

import ecopark.id.service.CashierService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;
@Path("/cashier")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CashierController {
    @Inject
    CashierService cashierService;

    @GET
    public Response get(){
        return cashierService.get();
    }

    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") Long id) {
        return cashierService.get(id);
    }

    @POST
    public Response post(Map<String, Object> request){
        return cashierService.post(request);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response put(@PathParam("id") Long id, Map<String, Object> request){
        return cashierService.put(id, request);
    }
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        return cashierService.delete(id);
    }
}
