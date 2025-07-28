package Lms.dao;

import dto.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Bookdao {
    public Book getBooksBySrno(Connection conn, int srno) throws SQLException {
        String query = "SELECT * FROM books WHERE serial_no = ?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, srno);

            try {
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        Book book = new Book();
                        book.setAuthorName(rs.getString("Author_key"));
                        book.setbookName(rs.getString("Name"));
                        book.setId(rs.getInt("Id"));
                        book.setbookQty(rs.getInt("Qnt"));
                        book.setSrNo(rs.getInt("serial_no"));
                        return book;
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return null;
    }

    public Book getBooksByAuthor(Connection conn, String authorName) throws SQLException {
        String query = "SELECT * FROM books WHERE author_key = ?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, authorName);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Book book = new Book();
                    book.setAuthorName(rs.getString("Author_key"));
                    book.setbookName(rs.getString("Name"));
                    book.setId(rs.getInt("Id"));
                    book.setbookQty(rs.getInt("Qnt"));
                    book.setSrNo(rs.getInt("Serial_no"));
                    return book;
                }
            }
        }

        return null;
    }

    public Book getBooksByAuthorOrName(Connection conn, String authorName, String name) throws SQLException {
        String query = "SELECT * FROM books WHERE author_key = ? AND name = ?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, authorName);
            ps.setString(2, name);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Book book = new Book();
                    book.setAuthorName(rs.getString("Author_key"));
                    book.setbookName(rs.getString("Name"));
                    book.setId(rs.getInt("Id"));
                    book.setbookQty(rs.getInt("Qnt"));
                    book.setSrNo(rs.getInt("Serial_no"));
                    return book;
                }
            }
        }

        return null;
    }

    public void addNewBook(Connection conn, Book book) throws SQLException {
        String query = "INSERT INTO books(Serial_no, name, author_key, qnt) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, book.getsrNo());
            ps.setString(2, book.getbookName());
            ps.setString(3, book.getAuthorName());
            ps.setInt(4, book.getbookQty());
            int cnt = ps.executeUpdate();
            if (cnt > 0) {
                System.out.println("✅ Book added successfully.");
            } else {
                System.out.println("⚠️ Failed to add book.");
            }
        }

    }

    public void updateBook(Connection conn, Book book) throws SQLException {
        String query = "UPDATE books set qnt = ? WHERE Author_key = ? AND Name = ?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, book.getbookQty());
            ps.setString(2, book.getAuthorName());
            ps.setString(3, book.getbookName());
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Book Info updated successfully.");
            } else {
                System.out.println("⚠️ Failed to update book info!!");
            }
        }

    }

    public List<Book> getAllBooks(Connection conn) throws SQLException {
        String query = "SELECT * FROM books";
        List<Book> books = new ArrayList<Book>();
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            try (ResultSet rs = ps.executeQuery()) {
                while(rs.next()) {
                    Book book = new Book();
                    book.setAuthorName(rs.getString("Author_key"));
                    book.setbookName(rs.getString("Name"));
                    book.setId(rs.getInt("Id"));
                    book.setbookQty(rs.getInt("Qnt"));
                    book.setSrNo(rs.getInt("Serial_no"));
                    books.add(book);
                }
            }
        }
        return books;
    }
}
