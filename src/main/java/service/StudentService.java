package service;

import Lms.dao.Bookdao;
import Lms.dao.StudentDao;
import dto.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class StudentService {
    Scanner sc = new Scanner(System.in);
    public void addStudent(Connection conn) throws SQLException {
        System.out.print("Enter Student Name: ");
       String studentName = sc.nextLine();
        System.out.print("Enter Student Registration_Number : ");
       String regNo =  sc.nextLine();
        StudentDao dao = new StudentDao();
      boolean bool =  dao.getStudentByReg_no(conn, regNo);
        if (bool) {
            System.out.println("⚠️ Student already exists");
            return ;
        }
        dao.saveStudent(conn, studentName, regNo);
    }
  public void getAllStudents(Connection conn) throws SQLException {
        StudentDao dao = new StudentDao();
        dao.getAllStudents(conn);
  }

}
