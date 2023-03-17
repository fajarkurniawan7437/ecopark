package ecopark.id.service;

import ecopark.id.model.Wahana;
import io.quarkus.mailer.Mail;
import org.apache.commons.digester.annotations.rules.ObjectCreate;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class WahanaService {

    public Response get(){
        return Response.status(Response.Status.OK).entity(Wahana.findAll().list()).build();
    }
    public Response get(Long id){
        Wahana wahana = Wahana.findById(id);
        if (wahana == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.status(Response.Status.OK).entity(wahana).build();
    }

    @Transactional
    public Response post(Map<String, Object> request){
        Wahana wahana = new Wahana();
        wahana.name = request.get("name").toString();
        wahana.height = request.get("height").toString();
        wahana.age = request.get("age").toString();
        wahana.persist();

        return Response.status(Response.Status.CREATED).entity(new HashMap<>()).build();
    }
    @Transactional
    public Response put(Long id, Map<String, Object> request){
        Wahana wahana =Wahana.findById(id);
        if (wahana == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        wahana.name = request.get("name").toString();
        wahana.height = request.get("height").toString();
        wahana.age = request.get("age").toString();
        wahana.persist();

        return Response.status(Response.Status.CREATED).entity(new HashMap<>()).build();
    }

    @Transactional
    public Response delete(Long id){
        Wahana wahana = Wahana.findById(id);
        if (wahana == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        wahana.delete();
        return Response.status(Response.Status.OK).entity(new HashMap<>()).build();
    }
}
