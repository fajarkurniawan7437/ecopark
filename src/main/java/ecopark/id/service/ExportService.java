package ecopark.id.service;

import com.opencsv.CSVWriter;
import ecopark.id.model.Costumer;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

@ApplicationScoped
public class ExportService {
    public Response exportPdfCostumer() throws JRException {
        File file = new File("src/main/resources/Ecopark_Green.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(Costumer.listAll());
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), jrBeanCollectionDataSource);
        byte[] jasperResult = JasperExportManager.exportReportToPdf(jasperPrint);

        return Response.ok().type("application/pdf").entity(jasperResult).build();
    }
    public Response exportExceCostumer() throws IOException {
        ByteArrayOutputStream outputStream = excelCostumer();

        return Response.ok()
                .type("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
                .header("Content-Disposition", "attachment; filename=\"costumer_excel.xlsx\"")
                .entity(outputStream.toByteArray()).build();
    }
    public ByteArrayOutputStream excelCostumer() throws IOException{
        List<Costumer> itemList = Costumer.listAll();
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("data");

        int rownum = 0;
        Row row = sheet.createRow(rownum++);
        row.createCell(0).setCellValue("id");
        row.createCell(1).setCellValue("name");
        row.createCell(2).setCellValue("gender");
        row.createCell(3).setCellValue("height");
        row.createCell(4).setCellValue("age");
        row.createCell(5).setCellValue("cashier");
        row.createCell(6).setCellValue("operasional");
        row.createCell(7).setCellValue("created_at");
        row.createCell(8).setCellValue("updated_at");

        for (Costumer costumer : itemList){
            row = sheet.createRow(rownum++);
            row.createCell(0).setCellValue(costumer.id);
            row.createCell(1).setCellValue(costumer.name);
            row.createCell(2).setCellValue(costumer.gender);
            row.createCell(3).setCellValue(costumer.height);
            row.createCell(4).setCellValue(costumer.age);
            row.createCell(5).setCellValue((RichTextString) costumer.cashier);
            row.createCell(6).setCellValue((RichTextString) costumer.operasional);
            row.createCell(7).setCellValue(costumer.createdAt.format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss")));
            row.createCell(8).setCellValue(costumer.updatedAt.format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss")));
        }
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        return outputStream;
    }

    public Response exportCsvCostumer() throws IOException{
        List<Costumer> itemList = Costumer.listAll();
        File file = File.createTempFile("temp","");
        FileWriter outputfile = new FileWriter(file);
        CSVWriter writer = new CSVWriter(outputfile);

        String[] headers = { "id", "name", "gender", "height", "cashier", "operasional", "age", "created_at", "updated_at"};
        writer.writeNext(headers);
        for (Costumer costumer : itemList){
            String[] data = {
                    costumer.id.toString(),
                    costumer.name,
                    costumer.gender,
                    costumer.height,
                    costumer.age,
                    costumer.cashier.toString(),
                    costumer.operasional.toString(),
                    costumer.createdAt.format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss")),
                    costumer.updatedAt.format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss"))
            };
            writer.writeNext(data);
        }
        return Response.ok()
                .type("text/csv")
                .header("Content-Disposition", "attachment; filename=\"costumer_csv.csv\"")
                .entity(file).build();
    }
}
