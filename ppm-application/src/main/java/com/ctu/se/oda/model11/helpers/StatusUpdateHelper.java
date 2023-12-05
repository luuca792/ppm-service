package com.ctu.se.oda.model11.helpers;

import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class StatusUpdateHelper {

    @Scheduled(cron = "*/10 * * * * *")
    public void statusUpdateJob() {
        log.info("Auto update status job executed");
    }
}
