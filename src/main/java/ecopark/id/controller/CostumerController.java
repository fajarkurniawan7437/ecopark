package ecopark.id.controller;

import ecopark.id.service.CostumerService;
import ecopark.id.service.ExportService;
import ecopark.id.service.OperasionalService;
import net.sf.jasperreports.engine.JRException;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Map;

@Path("/costumer")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CostumerController {

    @Inject
    CostumerService costumerService;
    @Inject
    ExportService exportService;

    @GET
    public Response get(){
        return costumerService.get();
    }
    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") Long id) {
        return costumerService.get(id);
    }

    @GET
    @Path("/export/pdf")
    @Produces("application/pdf")
    public Response exportPdf() throws JRException {
        return exportService.exportPdfCostumer();
    }

    @GET
    @Path("/export/excel")
    @Produces("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
    public Response exportExcel() throws IOException {
        return exportService.exportExceCostumer();
    }

    @GET
    @Path("/export/csv")
    @Produces("text/csv")
    public Response exportCsv() throws IOException {
        return exportService.exportCsvCostumer();
    }

    @POST
    public Response post(Map<String, Object> request){
        return costumerService.post(request);
    }



    @PUT
    @Path("/{id}")
    @Transactional
    public Response put(@PathParam("id") Long id, Map<String, Object> request){
        return costumerService.put(id, request);
    }
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        return costumerService.delete(id);
    }
}
