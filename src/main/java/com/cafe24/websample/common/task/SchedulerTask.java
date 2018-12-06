package com.cafe24.websample.common.task;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class SchedulerTask {
    private static final Logger log = LoggerFactory.getLogger(SchedulerTask.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() throws Exception {
        //memberService.selectMemberList();
        log.info("The time is now {}", dateFormat.format(new Date()));
    }
    @Scheduled(cron = "0/10 * * * * *")
    public void runEvery10Sec() {
        log.info("Cron expression - Run every 10 second - " + dateFormat.format(new Date()));
    }
}
