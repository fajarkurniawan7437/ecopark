package ecopark.id.service;

import ecopark.id.model.Operasional;
import ecopark.id.model.Wahana;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class OperasionalService {
    public Response get(){
        return Response.status(Response.Status.OK).entity(Operasional.findAll().list()).build();
    }

    public Response get(Long id){
        Operasional operasional = Operasional.findById(id);
        if (operasional == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.status(Response.Status.OK).entity(operasional).build();
    }

    @Transactional
    public Response post(Map<String, Object> request){
        Operasional operasional = new Operasional();
        operasional.name = request.get("name").toString();
        operasional.gender = request.get("gender").toString();
        operasional.persist();

        return Response.status(Response.Status.CREATED).entity(new HashMap<>()).build();
    }

    @Transactional
    public Response put(Long id, Map<String, Object> request){
        Operasional operasional = Operasional.findById(id);
        if (operasional == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        operasional.name = request.get("name").toString();
        operasional.gender = request.get("gender").toString();
        operasional.persist();

        return Response.status(Response.Status.CREATED).entity(new HashMap<>()).build();
    }

    @Transactional
    public Response delete(Long id){
        Operasional operasional = Operasional.findById(id);
        if (operasional == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        operasional.delete();
        return Response.status(Response.Status.OK).entity(new HashMap<>()).build();
    }

}
