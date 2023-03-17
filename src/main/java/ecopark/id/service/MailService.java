package ecopark.id.service;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;

@ApplicationScoped
public class MailService {
    @Inject
    Mailer mailer;
    @Inject
    ExportService exportService;

    public void sendEmail(String email){
        mailer.send(
                Mail.withHtml(email,
                        "CRUD API Quarkus Ecopark",
                        "<h1>Hello,</h1> this is Quarkus Costumer-Service"));
    }

    public void sendExcelToEmail(String email) throws IOException {
        mailer.send(
                Mail.withHtml(email,
                                "Excel Costumer Ecopark",
                                "<h1>Hello, Tugas</h1> this is your excel file")
                        .addAttachment("list-costumer.xlsx",
                                exportService.excelCostumer().toByteArray(),
                                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
    }
}
