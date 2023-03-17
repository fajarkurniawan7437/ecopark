package ecopark.id.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import ecopark.id.dto.FileFromDTO;
import ecopark.id.model.Costumer;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;

@ApplicationScoped
public class ImportService {
    @Transactional
    public Response importExcel(FileFromDTO request) throws IOException{
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(request.file);
        XSSFWorkbook workbook = new XSSFWorkbook(byteArrayInputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);
        sheet.removeRow(sheet.getRow(0));

        List<Costumer> toPersist = new ArrayList<>();
        for (Row row : sheet){
            Costumer costumer = new Costumer();
            costumer.name = row.getCell(0).getStringCellValue();
            costumer.gender = row.getCell(1).getStringCellValue();
            costumer.height = row.getCell(2).getStringCellValue();
            costumer.age = row.getCell(3).getStringCellValue();
            toPersist.add(costumer);
        }
        Costumer.persist(toPersist);
        return Response.status(Response.Status.CREATED).entity(new HashMap<>()).build();
    }

    @Transactional
    public Response importCsv(FileFromDTO request) throws IOException, CsvValidationException {
        File file = File.createTempFile("temp", "");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(request.file);

        CSVReader reader = new CSVReader(new FileReader(file));
        String[] nextLine;
        reader.skip(1);

        List<Costumer> toPersist = new ArrayList<>();
        while ((nextLine = reader.readNext()) != null) {
            Costumer costumer = new Costumer();
            costumer.name = nextLine[0].trim();
            costumer.gender = nextLine[1].trim();
            costumer.height = nextLine[2].trim();
            costumer.age = nextLine[3].trim();
            toPersist.add(costumer);
        }
        Costumer.persist(toPersist);
        return Response.status(Response.Status.CREATED).entity(new HashMap<>()).build();
    }
}
