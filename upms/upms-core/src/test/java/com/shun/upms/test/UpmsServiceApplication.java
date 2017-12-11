package com.shun.upms.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * User: mew <p />
 * Time: 17/11/10 14:52  <p />
 * Version: V1.0  <p />
 * Description: 服务启动类 <p />
 */
public class UpmsServiceApplication {

    private static Logger _log = LoggerFactory.getLogger(UpmsServiceApplication.class);

    public static void main(String[] args) {
        _log.info(">>>>> upms-core-service 正在启动 <<<<<");
        new ClassPathXmlApplicationContext("classpath:/spring/applicationContext.xml");
        _log.info(">>>>> upms-core-service 启动完成 <<<<<");
    }

}
