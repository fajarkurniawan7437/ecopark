package ecopark.id.service;

import ecopark.id.model.Cleaning;
import ecopark.id.model.Operasional;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;
@ApplicationScoped
public class CleaningService {
    public Response get(){
        return Response.status(Response.Status.OK).entity(Cleaning.findAll().list()).build();
    }
    public Response get(Long id){
        Cleaning cleaning = Cleaning.findById(id);
        if (cleaning == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.status(Response.Status.OK).entity(cleaning).build();
    }

    @Transactional
    public Response post(Map<String, Object> request){
        Cleaning cleaning = new Cleaning();
        cleaning.name = request.get("name").toString();
        cleaning.gender = request.get("gender").toString();
        cleaning.persist();

        return Response.status(Response.Status.CREATED).entity(new HashMap<>()).build();
    }

    @Transactional
    public Response put(Long id, Map<String, Object> request){
        Cleaning cleaning= Cleaning.findById(id);
        if (cleaning == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        cleaning.name = request.get("name").toString();
        cleaning.gender = request.get("gender").toString();
        cleaning.persist();

        return Response.status(Response.Status.CREATED).entity(new HashMap<>()).build();
    }

    @Transactional
    public Response delete(Long id){
        Cleaning cleaning = Cleaning.findById(id);
        if (cleaning == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        cleaning.delete();
        return Response.status(Response.Status.OK).entity(new HashMap<>()).build();
    }

}
