package edu.upc.dsa.CRUD.MYSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class FactorySession {

    //private IUserDAO em;
    public static Session openSession() {
        Connection conn = getConnection();

        Session session = new SessionImpl(conn);

        return session;
    }

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn =
                    DriverManager.getConnection("jdbc:mysql://localhost:3307/croacky?" + "user=root&password=toni");

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return conn;
    }
}
