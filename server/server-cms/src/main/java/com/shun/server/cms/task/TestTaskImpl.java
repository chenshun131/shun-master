package com.shun.server.cms.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * User: mew <p />
 * Time: 17/11/27 11:43  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
@Component
public class TestTaskImpl {

    @Scheduled(cron = "0 0/10 * * * ?")
    public void test() {
        System.out.println("Task");
    }

}
