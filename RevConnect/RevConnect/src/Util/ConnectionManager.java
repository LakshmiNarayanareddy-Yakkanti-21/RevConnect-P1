package Util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager {

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            return DriverManager.getConnection(
                    DatabaseConfig.URL,
                    DatabaseConfig.USER,
                    DatabaseConfig.PASSWORD
            );

        } catch (Exception e) {
            throw new RuntimeException("Database connection failed", e);
        }
    }
}
