package com.shun.framework.util;

import org.apache.commons.lang.ObjectUtils;
import org.apache.velocity.VelocityContext;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.shun.framework.util.StringUtil.lineToHump;
import static com.shun.framework.util.StringUtil.lineToHumpWithEndPo;

/**
 * User: mew <p />
 * Time: 17/11/7 09:29  <p />
 * Version: V1.0  <p />
 * Description: 代码生成类 <p />
 * 将会重新生成 Dao 的 po 以及 com.shun.upms.dao.com.shun.upms.core.dao.mapper 若存在则删除，对于 core 中的文件若存在则不修改否则创建
 */
public class MybatisGeneratorUtil {

    /** generatorConfig模板路径 */
    private static String generatorConfig_vm = "/template/generatorConfig.vm";

    /** Service模板路径 */
    private static String service_vm = "/template/Service.vm";

    /** ServiceMock模板路径 */
    private static String serviceMock_vm = "/template/ServiceMock.vm";

    /** ServiceImpl模板路径 */
    private static String serviceImpl_vm = "/template/ServiceImpl.vm";

    /** Dao模板路径 */
    private static String dao_vm = "/template/Dao.vm";

    /** Mapper模板路径 */
    private static String mapper_vm = "/template/Mapper.vm";

    /**
     * 根据模板生成generatorConfig.xml文件
     *
     * @param jdbc_driver
     *         驱动路径
     * @param jdbc_url
     *         链接
     * @param jdbc_username
     *         帐号
     * @param jdbc_password
     *         密码
     * @param module
     *         项目模块
     * @param database
     *         数据库
     * @param table_prefix
     *         表前缀
     * @param package_name
     *         包名
     */
    public static void generator(String jdbc_driver,
                                 String jdbc_url,
                                 String jdbc_username,
                                 String jdbc_password,
                                 String module,
                                 String database,
                                 String table_prefix,
                                 String package_name,
                                 Map<String, String> last_insert_id_tables) throws Exception {

        generatorConfig_vm = MybatisGeneratorUtil.class.getResource(generatorConfig_vm).getPath().replaceFirst("/", "");
        service_vm = MybatisGeneratorUtil.class.getResource(service_vm).getPath().replaceFirst("/", "");
        serviceMock_vm = MybatisGeneratorUtil.class.getResource(serviceMock_vm).getPath().replaceFirst("/", "");
        serviceImpl_vm = MybatisGeneratorUtil.class.getResource(serviceImpl_vm).getPath().replaceFirst("/", "");

        String targetProject = module + "/" + module + "-dao";
        // 显示 主工程根目录
        String basePath = MybatisGeneratorUtil.class.getResource("/").getPath().replace("/target/classes/", "")
                .replace(targetProject, "");
        String generatorConfig_xml = MybatisGeneratorUtil.class.getResource("/").getPath().replace
                ("/target/classes/", "") + "/src/main/resources/generatorConfig.xml";
        targetProject = basePath + targetProject;
        String sql = "SELECT table_name FROM INFORMATION_SCHEMA.TABLES WHERE table_schema = '" + database
                + "' AND table_name LIKE '" + table_prefix + "_%';";

        System.out.println("========== 开始生成generatorConfig.xml文件 ==========");
        List<Map<String, Object>> tables = new ArrayList<>();
        try {
            VelocityContext context = new VelocityContext();
            Map<String, Object> table;

            // 查询定制前缀项目的所有表
            JdbcUtil jdbcUtil = new JdbcUtil(jdbc_driver, jdbc_url, jdbc_username, AESUtil.AESDecode(jdbc_password));
            List<Map> result = jdbcUtil.selectByParams(sql, null);
            for (Map map : result) {
                System.out.println(map.get("TABLE_NAME"));
                table = new HashMap<>();
                table.put("table_name", map.get("TABLE_NAME"));
                table.put("model_name", lineToHumpWithEndPo(ObjectUtils.toString(map.get("TABLE_NAME"))));
                table.put("model_name2", lineToHump(ObjectUtils.toString(map.get("TABLE_NAME"))));
                tables.add(table);
            }
            jdbcUtil.release();

            String targetProject_sqlMap = basePath + module + "/" + module + "-dao";
            context.put("tables", tables);
            context.put("generator_jdbc_driver", jdbc_driver);
            context.put("generator_jdbc_url",
                    (jdbc_url.contains("&amp;") ? jdbc_url : jdbc_url.replaceAll("&", "&amp;"))); // 需要转义
            context.put("generator_jdbc_username", jdbc_username);
            context.put("generator_jdbc_password", AESUtil.AESDecode(jdbc_password));
            context.put("generator_javaModelGenerator_targetPackage", package_name + ".dao.po");
            context.put("generator_sqlMapGenerator_targetPackage", package_name + ".dao.mapper");
            context.put("generator_javaClientGenerator_targetPackage", package_name + ".dao.mapper");
            context.put("targetProject", targetProject);
            context.put("targetProject_sqlMap", targetProject_sqlMap);
            context.put("last_insert_id_tables", last_insert_id_tables);
            VelocityUtil.generate(generatorConfig_vm, generatorConfig_xml, context);
            // 删除旧代码
            deleteDir(new File(targetProject + "/src/main/java/" +
                    package_name.replaceAll("\\.", "/") + "/dao/po"));
            deleteDir(new File(targetProject + "/src/main/java/" +
                    package_name.replaceAll("\\.", "/") + "/dao/mapper"));
            deleteDir(new File(targetProject_sqlMap + "/src/main/java/" +
                    package_name.replaceAll("\\.", "/") + "/dao/mapper"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("========== 结束生成generatorConfig.xml文件 ==========");

        System.out.println("========== 开始运行MybatisGenerator ==========");
        List<String> warnings = new ArrayList<>();
        File configFile = new File(generatorConfig_xml);
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(true);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
        for (String warning : warnings) {
            System.out.println(warning);
        }
        // 修改 model，解决 MyBatis generator 产生的 Bug
        updateModelMapper(targetProject + "/src/main/java/" +
                package_name.replaceAll("\\.", "/") + "/dao/mapper");
        updateModelMapperXML(targetProject + "/src/main/resources/" +
                package_name.replaceAll("\\.", "/") + "/dao/mapper");
        System.out.println("========== 结束运行MybatisGenerator ==========");

        System.out.println("========== 开始生成Service 以及 Dao ==========");
        String ctime = new SimpleDateFormat("yyyy/M/d HH:mm").format(new Date());
        String user = System.getenv().get("USER"); // 系统用户名称

        // 生成自定义文件对应的路径
        String servicePath = basePath + module + "/" + module + "-api" + "/src/main/java/" + package_name
                .replaceAll("\\.", "/") + "/api";
        String serviceImplPath = basePath + module + "/" + module + "-core" + "/src/main/java/" + package_name
                .replaceAll("\\.", "/") + "/core/service/impl";
        String daoPath = basePath + module + "/" + module + "-core" + "/src/main/java/" + package_name
                .replaceAll("\\.", "/") + "/core/dao";
        String mapperPath = basePath + module + "/" + module + "-core" + "/src/main/java/" + package_name
                .replaceAll("\\.", "/") + "/core/dao/mapper";

        for (int i = 0; i < tables.size(); i++) {
            String modelPo = lineToHumpWithEndPo(ObjectUtils.toString(tables.get(i).get("table_name"))); // 带 Po 结尾
            String model = lineToHump(ObjectUtils.toString(tables.get(i).get("table_name"))); // 不带 Po 结尾
            String service = servicePath + "/" + model + "Service.java";
            String serviceMock = servicePath + "/" + model + "ServiceMock.java";
            String serviceImpl = serviceImplPath + "/" + model + "ServiceImpl.java";
            String dao = daoPath + "/" + model + "Dao.java";
            String mapper = mapperPath + "/" + model + "InfoMapper.java";

            // 生成service
            File serviceFile = new File(service);
            if (!serviceFile.exists()) {
                VelocityContext context = new VelocityContext();
                context.put("package_name", package_name);
                context.put("model", modelPo);
                context.put("model2", model); // 不加 Po 尾缀
                context.put("ctime", ctime);
                context.put("user", user);
                VelocityUtil.generate(service_vm, service, context);
                System.out.println(service);
            }

            // 生成serviceMock
            File serviceMockFile = new File(serviceMock);
            if (!serviceMockFile.exists()) {
                VelocityContext context = new VelocityContext();
                context.put("package_name", package_name);
                context.put("model", modelPo);
                context.put("model2", model); // 不加 Po 尾缀
                context.put("ctime", ctime);
                context.put("user", user);
                VelocityUtil.generate(serviceMock_vm, serviceMock, context);
                System.out.println(serviceMock);
            }

            // 生成serviceImpl
            File serviceImplFile = new File(serviceImpl);
            if (!serviceImplFile.exists()) {
                VelocityContext context = new VelocityContext();
                context.put("package_name", package_name);
                context.put("model", modelPo);
                context.put("model2", model); // 不加 Po 尾缀
                context.put("model3", StringUtil.toLowerCaseFirstOne(model)); // 不加 Po 尾缀，首字母小写
                context.put("mapper", StringUtil.toLowerCaseFirstOne(modelPo));
                context.put("ctime", ctime);
                context.put("user", user);
                VelocityUtil.generate(serviceImpl_vm, serviceImpl, context);
                System.out.println(serviceImpl);
            }

            // 生成 Dao
            File daoFile = new File(dao);
            if (!daoFile.exists()) {
                VelocityContext context = new VelocityContext();
                context.put("package_name", package_name);
                context.put("model", modelPo); // 加 Po 尾缀
                context.put("model2", model); // 不加 Po 尾缀
                context.put("model3", StringUtil.toLowerCaseFirstOne(model)); // 不加 Po 尾缀，首字母小写
                context.put("ctime", ctime);
                context.put("user", user);
                VelocityUtil.generate(dao_vm, dao, context);
                System.out.println(dao);
            }

            // 生成 Dao 中 mapper 和 po 目录
            File mapperPathFile = new File(daoPath + "/mapper");
            if (!daoFile.exists()) {
                daoFile.mkdir();
            }
            File poPathFile = new File(daoPath + "/po");
            if (!poPathFile.exists()) {
                poPathFile.mkdir();
            }


//            // 生成 com.shun.upms.dao.com.shun.upms.core.dao.mapper (有点复杂，还是手动创建)
//            File mapperFile = new File(com.shun.upms.dao.com.shun.upms.core.dao.mapper);
//            if (!mapperFile.exists()) {
//                VelocityContext context = new VelocityContext();
//                context.put("package_name", package_name);
//                context.put("model", model); // 不加 Po 尾缀
//                context.put("ctime", ctime);
//                context.put("user", user);
//                VelocityUtil.generate(mapper_vm, com.shun.upms.dao.com.shun.upms.core.dao.mapper, context);
//                System.out.println(com.shun.upms.dao.com.shun.upms.core.dao.mapper);
//            }
        }
        System.out.println("========== 结束生成Service 以及 Dao ==========");
    }

    /**
     * 递归删除非空文件夹
     *
     * @param dir
     */
    private static void deleteDir(File dir) {
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            assert files != null;
            for (File file : files) {
                deleteDir(file);
            }
        }
        dir.delete();
    }

    private static void updateModelMapper(String filePath) {
        File mapperDir = new File(filePath);
        BufferedReader reader = null;
        File[] mapperFiles = mapperDir.listFiles();
        if (mapperFiles != null) {
            for (File mapperFile : mapperFiles) {
                // 读取对应文件内容
                StringBuilder contentSB = new StringBuilder();
                try {
                    reader = new BufferedReader(new FileReader(mapperFile));
                    String tempString = null;
                    boolean isNotUpdateInterface = true, isNotUpdateFunc = true;
                    while ((tempString = reader.readLine()) != null) {
                        if (isNotUpdateInterface && tempString.contains("interface")) {
                            String poName = tempString.split(" ")[2].replace("Mapper", "");
                            tempString = tempString.replace("{", "").trim();
                            tempString = tempString + "<" + poName + ", " + poName + "Example> {";
                            isNotUpdateInterface = false;
                        }
                        if (isNotUpdateFunc && tempString.contains("countByExample")) {
                            tempString = tempString.trim();
                            if (tempString.startsWith("int")) {
                                tempString = "    " + tempString.replace("int", "long");
                            }
                            isNotUpdateFunc = false;
                        }
                        contentSB.append(tempString).append("\n");
                    }
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                // 写入文件
                BufferedWriter bufferWritter = null;
                try {
                    FileWriter fileWritter = new FileWriter(mapperFile);
                    bufferWritter = new BufferedWriter(fileWritter);
                    bufferWritter.write(contentSB.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (bufferWritter != null) {
                        try {
                            bufferWritter.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    private static void updateModelMapperXML(String filePath) {
        File mapperDir = new File(filePath);
        BufferedReader reader = null;
        File[] mapperFiles = mapperDir.listFiles();
        if (mapperFiles != null) {
            for (File mapperFile : mapperFiles) {
                // 读取对应文件内容
                StringBuilder contentSB = new StringBuilder();
                try {
                    reader = new BufferedReader(new FileReader(mapperFile));
                    String tempString = null;
                    while ((tempString = reader.readLine()) != null) {
                        if (tempString.contains("<cache type=\"org.mybatis.caches.ehcache.LoggingEhcache\" />")) {
                            contentSB.append(tempString).append("\n");
                            contentSB.append("</mapper>");
                            break;
                        }
                        if (tempString.contains("id=\"countByExample\"")) {
                            tempString = tempString.replace("java.lang.Integer", "long");
                        }
                        contentSB.append(tempString).append("\n");
                    }
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                // 写入文件
                BufferedWriter bufferWritter = null;
                try {
                    FileWriter fileWritter = new FileWriter(mapperFile);
                    bufferWritter = new BufferedWriter(fileWritter);
                    bufferWritter.write(contentSB.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (bufferWritter != null) {
                        try {
                            bufferWritter.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

}
