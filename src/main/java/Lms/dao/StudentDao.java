package Lms.dao;

import dto.Book;
import dto.BookingDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {
    public void saveStudent(Connection conn, String studentName, String reg_no) throws SQLException {
        String query = "INSERT INTO students (student_name, reg_no) VALUES (?, ?)";
        try(PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, studentName);
            ps.setString(2, reg_no);
            int cnt = ps.executeUpdate();
            if(cnt > 0){
                System.out.println("✅ Student added successfully");
            }else{
                System.out.println("⚠️ Failed to add student.");
            }
        }

    }
    public Boolean getStudentByReg_no(Connection conn, String regNo) throws SQLException {
        String query = "SELECT * FROM students WHERE reg_no = ?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, regNo);

            try {
                try (ResultSet rs = ps.executeQuery()) {
                  return rs.next();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public int getStudentByRegno_(Connection conn, String regNo) throws SQLException {
        String query = "select * from students where reg_no = ?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, regNo);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }

        return 0;
    }
    public void getAllStudents(Connection conn) throws SQLException {
        String query = "SELECT * FROM students";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.print(rs.getString("student_name") + "         ");
                System.out.print(rs.getInt("Id") + "         ");
                System.out.print(rs.getString("reg_no") + "         \n");
            }
        }
    }
    public void saveBookingDetails(Connection conn, int Std_Id, int Book_Id, int Qty) throws SQLException {
        String query = "INSERT INTO bookdetails(Std_Id, Book_Id, Qty) VALUES(?,?,?)" ;
        try(PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, Std_Id);
            ps.setInt(2, Book_Id);
            ps.setInt(3, Qty);

            int cnt  = ps.executeUpdate();
            if(cnt > 0) System.out.println("✅ Booking details saved successfully");
            else  System.out.println("⚠️ Booking details not saved");
        }
    }
    public List<BookingDetails> getBookDetailsId(Connection conn, int stdId) throws SQLException {
        String query = "SELECT * FROM bookdetails bd "
                + "INNER JOIN books b ON b.id = bd.book_id "
                + "WHERE bd.std_id = ?";

        List<BookingDetails> bookingDetails = new ArrayList<>();

        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, stdId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                BookingDetails bookingDetail = new BookingDetails();
                bookingDetail.setAuthor(resultSet.getString("author_key"));
                bookingDetail.setBook_id(resultSet.getInt("Id"));
                bookingDetail.setBookName(resultSet.getString("name"));
                bookingDetail.setqnt(resultSet.getInt("qnt"));
                bookingDetail.setStd_id(resultSet.getInt("std_id"));
                bookingDetail.setsrNo(resultSet.getInt("serial_no"));
                bookingDetail.setId(resultSet.getInt("id"));

                bookingDetails.add(bookingDetail);
            }
        }

        return bookingDetails;
    }
    public void deleteBookingDetails(Connection conn, int id) throws SQLException {
        String query = " DELETE FROM bookdetails WHERE Id = ?" ;
       try(PreparedStatement ps = conn.prepareStatement(query)){
           ps.setInt(1,id);
         ps.executeUpdate();
       }

    }
}
