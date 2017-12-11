package com.shun.framework.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * User: mew <p />
 * Time: 17/11/8 10:00  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class SystemConfiguration {

    private static Log log = LogFactory.getLog(SystemConfiguration.class);

    private static Properties props = new Properties();

    public SystemConfiguration() {
    }

    public static String getProperty(String key) {
        return props.getProperty(key);
    }

    public static void loadConfigFile(String file) {
        try {
            InputStream is = ClassLoader.getSystemResourceAsStream(file);
            props.load(is);
        } catch (IOException var2) {
            log.error("Config file \"system.properties\" not found.", var2);
        }
    }

    public static String getProperty(String file, String key) {
        Properties props = new Properties();
        try {
            InputStream is = ClassLoader.getSystemResourceAsStream(file);
            props.load(is);
        } catch (IOException var4) {
            log.error("Config file \"" + file + "\" not found.", var4);
        }
        return props.getProperty(key);
    }

    static {
        try {
            InputStream is = ClassLoader.getSystemResourceAsStream("system.properties");
            props.load(is);
        } catch (IOException var1) {
            log.error("Config file \"system.properties\" not found.", var1);
        }
    }

}
