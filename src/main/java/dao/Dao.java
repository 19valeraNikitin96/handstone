package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

abstract class Dao {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://contac01.mysql.ukraine.com.ua:3306/contac01_prilosh";
    private static final String LOGIN = "contac01_prilosh";
    private static final String PASS = "4h57futz";

    Connection getConnection() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException ex) {
            System.out.println("====Driver not loaded");
        }
        Connection c = null;
        try {
            c = DriverManager.getConnection(URL, LOGIN, PASS);
        } catch (SQLException ex) {
            System.out.println("====Connection not created");
        }
        return c;
    }
}
