package Lms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseService {
    public static Connection conn;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/library";
    private static final String USER = "root";
    private static final String PASS = "root";

    private static Connection createConn() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "root");
            System.out.println("✅ Connection established ");
            return conn;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to DB", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return conn != null && !conn.isClosed() ? conn : createConn();
    }
}
