package Login;

import Lms.dao.DatabaseService;
import Lms.dao.loginDao;
import Lms.dao.loginDao;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class ToLogin {
    public void inLogin() {
        System.out.print("Please enter your username : ");
        Scanner sc = new Scanner(System.in);
        String username = sc.nextLine();
        System.out.print("Please enter your password : ");
        String password = sc.next();

        try {
            try (Connection conn = DatabaseService.getConnection()) {
                loginDao log = new loginDao();
                String usertype = log.Tologin(conn, username, password);
                if (usertype == null) {
                    System.out.println("⚠️ Invalid username or password");
                    return;
                }

                System.out.println("✅ Successfully logged in as: " + usertype);
                if (usertype.equals("admin")) {
                    menu temp = new menu();
                    temp.displayAdminMenu(conn);
                } else{
                    menu temp = new menu();
                    temp.displayStudentMenu(conn);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
