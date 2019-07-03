package com.tww.test.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class GenRepairCustomerSql {
    public static final String driver = "com.mysql.jdbc.Driver";
    public static final String url3306 = "jdbc:mysql://117.50.62.185:3306/highso";
    public static final String url3308 = "jdbc:mysql://117.50.62.185:3308/crm";
    public static final String username = "prod-crm-read";
    public static final String password = "b5L9vfGYPbiL";
    public static final String sqlDup = "select cs.id,cs.customerId,cs.mobile\n" +
            "from crmcustomer cs\n" +
            "inner join (select cs_in.customerId\n" +
            "from crmcustomer cs_in\n" +
            "WHERE cs_in.customerId is not null \n" +
            "GROUP BY cs_in.customerId HAVING count(cs_in.id) > 1) as dup on dup.customerId = cs.customerId \n";

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Class.forName(driver);
        try (Connection connection3306 = DriverManager.getConnection(url3308, username, password)) {
            Statement statement = connection3306.prepareStatement(sqlDup);
        }
    }
}
