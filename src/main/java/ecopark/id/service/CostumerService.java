package ecopark.id.service;

import ecopark.id.model.Costumer;
import ecopark.id.model.Operasional;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;
@ApplicationScoped
public class CostumerService {
    public Response get(){
        return Response.status(Response.Status.OK).entity(Costumer.findAll().list()).build();
    }

    public Response get(Long id){
        Costumer costumer = Costumer.findById(id);
        if (costumer == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.status(Response.Status.OK).entity(costumer).build();
    }

    @Transactional
    public Response post(Map<String, Object> request){
        Costumer costumer = new Costumer();
        costumer.name = request.get("name").toString();
        costumer.gender = request.get("gender").toString();
        costumer.height = request.get("height").toString();
        costumer.age = request.get("age").toString();
        costumer.persist();

        return Response.status(Response.Status.CREATED).entity(new HashMap<>()).build();
    }

    @Transactional
    public Response put(Long id, Map<String, Object> request){
        Costumer costumer = Costumer.findById(id);
        if (costumer == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        costumer.name = request.get("name").toString();
        costumer.gender = request.get("gender").toString();
        costumer.height = request.get("height").toString();
        costumer.age = request.get("age").toString();
        costumer.persist();

        return Response.status(Response.Status.CREATED).entity(new HashMap<>()).build();
    }

    @Transactional
    public Response delete(Long id){
        Costumer costumer = Costumer.findById(id);
        if (costumer == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        costumer.delete();
        return Response.status(Response.Status.OK).entity(new HashMap<>()).build();
    }
}
