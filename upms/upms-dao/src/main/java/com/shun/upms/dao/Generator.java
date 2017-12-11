package com.shun.upms.dao;

import com.shun.framework.util.MybatisGeneratorUtil;
import com.shun.framework.util.PropertiesFileUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * User: mew <p />
 * Time: 17/11/6 17:33  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Generator {

    // 根据命名规范，只修改此常量值即可
    private static String MODULE = "upms";

    private static String DATABASE = "zheng";

    private static String TABLE_PREFIX = "upms_";

    private static String PACKAGE_NAME = "com.shun.upms";

    private static String JDBC_DRIVER = PropertiesFileUtil.getInstance("generator").get("generator.jdbc.driver");

    private static String JDBC_URL = PropertiesFileUtil.getInstance("generator").get("generator.jdbc.url");

    private static String JDBC_USERNAME = PropertiesFileUtil.getInstance("generator").get("generator.jdbc.username");

    private static String JDBC_PASSWORD = PropertiesFileUtil.getInstance("generator").get("generator.jdbc.password");

    /** 需要 insert 后返回主键的表配置，key:表名, value:主键名 */
    private static Map<String, String> LAST_INSERT_ID_TABLES = new HashMap<>();

    static {
        LAST_INSERT_ID_TABLES.put("upms_user", "user_id");
    }

    /**
     * 自动代码生成
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {
        MybatisGeneratorUtil.generator(
                JDBC_DRIVER,
                JDBC_URL,
                JDBC_USERNAME,
                JDBC_PASSWORD,
                MODULE,
                DATABASE,
                TABLE_PREFIX,
                PACKAGE_NAME,
                LAST_INSERT_ID_TABLES);
    }

}
