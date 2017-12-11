package com.shun.cms.dao.test;

import org.junit.Test;

import java.sql.*;

/**
 * User: mew <p />
 * Time: 17/11/14 15:12  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class T1 {

    @Test
    public void t1() {
        Connection con = null;
        PreparedStatement pst = null;

        String url = "jdbc:mysql://192.168.242.134:3306/";
        String db = "test";
        String driver = "com.mysql.cj.jdbc.Driver";
        String user = "root";
        String pass = "123456";

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url + db + "?useServerPrepStmts=false&rewriteBatchedStatements=true",
                    user, pass);
            con.setAutoCommit(false);// 禁止自动提交

            for (int i = 1; i < 90000; i++) {
                String sql = "insert into user(name, age, phone, email, sex, locked) VALUES (?, ?, ?, ?, ?, ?)";
                pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                pst.setString(1, "a" + i);
                pst.setString(2, String.valueOf(i));
                pst.setString(3, "15623459526");
                pst.setString(4, "1539831174@qq.com");
                pst.setString(5, "1");
                pst.setString(6, "0");
                pst.executeUpdate();
            }
            pst.executeBatch(); // 执行批量处理
            con.commit();  // 提交
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
