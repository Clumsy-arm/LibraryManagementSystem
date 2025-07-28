import Login.ToLogin;

import java.nio.channels.NonWritableChannelException;

public class LibraryManagementSystem {
        public static void main(String[] args) {
            System.out.println("WELCOME TO COLLEGE LIBRARY");
            System.out.println("Please login");
            ToLogin login = new ToLogin();
            login.inLogin();
        }
}
