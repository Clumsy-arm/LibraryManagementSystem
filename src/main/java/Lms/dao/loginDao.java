package Lms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class loginDao {
    public String Tologin(Connection conn, String User_name, String password) {
        String query = "SELECT * FROM login WHERE User_name = ? AND password = ?";

        try {
            String var7;
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setString(1, User_name);
                ps.setString(2, password);

                try (ResultSet rs = ps.executeQuery()) {
                    if (!rs.next()) {
                        return null;
                    }

                    var7 = rs.getString("user_type");
                }
            }

            return var7;
        } catch (SQLException e) {
            System.out.println("⚠️ Database error during login: " + e.getMessage());
            return null;
        }
    }
}
