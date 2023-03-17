package ecopark.id.service;

import ecopark.id.model.Cashier;
import ecopark.id.model.Operasional;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;
@ApplicationScoped
public class CashierService {
    public Response get(){
        return Response.status(Response.Status.OK).entity(Cashier.findAll().list()).build();
    }

    public Response get(Long id){
        Cashier cashier = Cashier.findById(id);
        if (cashier == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.status(Response.Status.OK).entity(cashier).build();
    }

    @Transactional
    public Response post(Map<String, Object> request){
        Cashier cashier = new Cashier();
        cashier.name = request.get("name").toString();
        cashier.gender = request.get("gender").toString();
        cashier.persist();

        return Response.status(Response.Status.CREATED).entity(new HashMap<>()).build();
    }

    @Transactional
    public Response put(Long id, Map<String, Object> request){
        Cashier cashier = Cashier.findById(id);
        if (cashier == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        cashier.name = request.get("name").toString();
        cashier.gender = request.get("gender").toString();
        cashier.persist();

        return Response.status(Response.Status.CREATED).entity(new HashMap<>()).build();
    }

    @Transactional
    public Response delete(Long id){
        Cashier cashier = Cashier.findById(id);
        if (cashier == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        cashier.delete();
        return Response.status(Response.Status.OK).entity(new HashMap<>()).build();
    }

}
