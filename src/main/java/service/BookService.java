package service;

import Lms.dao.Bookdao;
import Lms.dao.StudentDao;
import dto.Book;
import dto.BookingDetails;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class BookService {
    Scanner sc;

    public BookService() {
        this.sc = new Scanner(System.in);
    }

    public void searchBySrno(Connection conn) throws SQLException {
        System.out.println("Enter Srno:");
        int srno = this.sc.nextInt();
        Bookdao dao = new Bookdao();
        Book book = dao.getBooksBySrno(conn, srno);
        if (book != null) {
            System.out.println("The Book you are searching for is");
            PrintStream var10000 = System.out;
            String var10001 = book.getbookName();
            var10000.println("Book_name : " + var10001 + " Serial_No : " + book.getsrNo() + " Author_Name : " + book.getAuthorName());
        } else {
            System.out.println("⚠️ No such Book exists with serial_number: " + srno);
        }

    }

    public void searchByAuthor_name(Connection conn) throws SQLException {
        System.out.println("Enter Author name:");
        String aut = this.sc.nextLine();
        Bookdao dao = new Bookdao();
        Book book = dao.getBooksByAuthor(conn, aut);
        if (book != null) {
            System.out.println("The Book you are searching for is");
            PrintStream var10000 = System.out;
            String var10001 = book.getbookName();
            var10000.println("Book_name : " + var10001 + "\n Serial_No : " + book.getsrNo() + "\n Author_Name : " + book.getAuthorName());
        } else {
            System.out.println("⚠️ No such Book exists with Author name : " + aut);
        }

    }

    public void addBook(Connection conn) throws SQLException {
        System.out.print("Enter Srno: ");
        int srno = this.sc.nextInt();
        this.sc.nextLine();
        System.out.print("Enter Author name: ");
        String aut = this.sc.nextLine();
        System.out.print("Enter Book name: ");
        String bookName = this.sc.nextLine();
        System.out.println("Enter Quantity: ");
        int Qnt = this.sc.nextInt();
        Bookdao dao = new Bookdao();
        Book book = dao.getBooksByAuthorOrName(conn, aut, bookName);
        if (book != null) {
            System.out.println("⚠️ The Book you are trying to add already exists");
        } else {
            Book add = new Book();
            add.setAuthorName(aut);
            add.setSrNo(srno);
            add.setbookName(bookName);
            add.setbookQty(Qnt);
            dao.addNewBook(conn, add);
        }
    }

    public void updateQnt(Connection conn) throws SQLException {
        System.out.print("Enter Author name : ");
        String aut = this.sc.nextLine();
        System.out.print("Enter Book name: ");
        String bookName = this.sc.nextLine();
        Bookdao dao = new Bookdao();
        Book book = dao.getBooksByAuthorOrName(conn, aut, bookName);
        if (book == null) {
            System.out.println("⚠️ No such book found!!");
        } else {
            System.out.println("Enter the Quantity: ");
            int cnt = this.sc.nextInt();
            book.setbookQty(cnt + book.getbookQty());
            dao.updateBook(conn, book);
            System.out.println("✅ successfully updated");
        }
    }
    public void getAllBooks(Connection conn) throws SQLException {
        Bookdao dao = new Bookdao();
       List<Book> books = dao.getAllBooks(conn);
       for(Book book : books){
           System.out.println();
           System.out.print( "Sr_no : " + book.getsrNo() + "       ");
           System.out.print("Book_name : " + book.getbookName()+ "       ");
           System.out.print("Author : " + book.getAuthorName()+ "       ");
           System.out.print("Copies avilable : " + book.getbookQty()+ "       \n");

       }
    }
    public void checkOutBook(Connection conn) throws SQLException {
        StudentDao dao = new StudentDao();
        System.out.println("Enter Student Registration Number: ");
        String regNo  =  sc.nextLine();
      boolean ifExist = dao.getStudentByReg_no(conn,regNo);
      if (!ifExist) {
          System.out.println("Student registration number does not exist, get registered at admin");
          return;
      }
      getAllBooks(conn);

      System.out.println("Enter serial Number of required book : ") ;
      int srno = this.sc.nextInt();
      Bookdao dao1 = new Bookdao();
      Book book =  dao1.getBooksBySrno(conn, srno);
     if(book == null){
         System.out.println("Book is not available.") ;
         return;
     }
        book.setbookQty(book.getbookQty() - 1);
     int id = dao.getStudentByRegno_(conn, regNo);
     dao.saveBookingDetails(conn, id, book.getId(),1);
     dao1.updateBook(conn, book);
    }
    public void checkInBook(Connection conn) throws SQLException {
        StudentDao dao = new StudentDao();
        System.out.println("Enter Student Registration Number: ");
        String regNo  =  sc.nextLine();
        boolean ifExist = dao.getStudentByReg_no(conn,regNo);
        if (!ifExist) {
            System.out.println("Student registration number does not exist, get registered at admin");
        }




        int id = dao.getStudentByRegno_(conn, regNo);
     List<BookingDetails> bd =   dao.getBookDetailsId(conn, id);
     for(BookingDetails book : bd){
         System.out.println("Serial_no: " + book.getsrNo()+ "  Author: "+ book.getAuthor() + "  Title: "+ book.getBookName());
     }
        System.out.println("Enter Serial Number of Book to be Checked In:");
        int sNo = sc.nextInt();
        BookingDetails filterDetails = null;
        for (BookingDetails book : bd) {
            if (book.getsrNo() == sNo) {
                filterDetails = book;
                break;
            }
        }

        if (filterDetails == null) {
            throw new RuntimeException("Booking not found for srNo: " + sNo);
        }
        Bookdao dao1 = new Bookdao();
        Book book = dao1.getBooksBySrno(conn, sNo) ;
        book.setbookQty(book.getbookQty() + 1);
        dao1.updateBook(conn, book);
        dao.deleteBookingDetails(conn, id);
    }
}
