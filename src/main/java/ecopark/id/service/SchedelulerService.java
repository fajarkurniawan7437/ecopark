package ecopark.id.service;

import io.quarkus.scheduler.Scheduled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDateTime;

@ApplicationScoped
public class SchedelulerService {
    @Inject
    MailService mailService;

    Logger logger = LoggerFactory.getLogger(SchedelulerService.class);

    @Scheduled(every = "5m")
    public void generateKawahEdukasi(){
        logger.info("Ecopark_{}", LocalDateTime.now());
    }
}
