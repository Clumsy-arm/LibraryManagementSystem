
package Login;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import Lms.dao.Bookdao;
import service.BookService;
import service.StudentService;

public class menu {
    public void displayAdminMenu(Connection conn) throws SQLException {
        Scanner sc = new Scanner(System.in);
        StudentService studentService = new StudentService();
        BookService bookService = new BookService();
        int choice;
        do {
            System.out.println("Welcome Admin Menu");
            System.out.println("1. Search a book ");
            System.out.println("2. Add new book ");
            System.out.println("3. Update quantity of books ");
            System.out.println("4. Show all books ");
            System.out.println("5. Register Student ");
            System.out.println("6. Show registered students");
            System.out.println("7. Quit ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    this.searchBook(conn);
                    break;
                case 2:
                    bookService.addBook(conn);
                    break;
                case 3:
                    bookService.updateQnt(conn);
                case 4:
                    bookService.getAllBooks(conn);
                    break;
                case 5:
                    studentService.addStudent(conn);
                    break;
                case 6:
                    studentService.getAllStudents(conn);
                    break;
                case 7:
                    System.out.println("Thank You! Visit us Again");
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        } while(choice != 7);

    }

    private void searchBook(Connection conn) throws SQLException {
        BookService bookService = new BookService();
        System.out.println("1.Enter the book's serial number");
        System.out.println("2.Enter author name ");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        switch (choice) {
            case 1 -> bookService.searchBySrno(conn);
            case 2 -> bookService.searchByAuthor_name(conn);
        }

    }
    public void displayStudentMenu(Connection conn) throws SQLException {
        StudentService studentService = new StudentService();
        BookService bookService = new BookService();
        Scanner sc =  new Scanner(System.in) ;
        int choice;
        do{
            System.out.println("Welcome Student Menu");
            System.out.println("1. Search a book ");
            System.out.println("2. Checkout a book ");
            System.out.println("3. Checkin a book ");
            System.out.println("4. Quit ");
            choice = sc.nextInt();
            switch(choice) {
                case 1 : searchBook(conn);
                    break;
                case 2: bookService.checkOutBook(conn);
                    break;
                case 3: bookService.checkInBook(conn);
                    break;
                case 4:
                    System.out.println("Thank You! Visit us Again");
            }

        } while(choice != 4) ;
    }
}
