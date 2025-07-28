package dto;

public class BookingDetails {
    public int id;

    public int std_id;
    public int book_id;


    public String author;
    public String bookName;

    public int qnt;
    public int srNo;

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }
    public void setStd_id(int std_id) {
        this.std_id = std_id;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
    public void  setqnt(int qnt) {
        this.qnt = qnt;
    }
    public void setsrNo(int srNo) {
        this.srNo = srNo;
    }
    public int getId() {
        return  id;
    }
    public int getStd_id() {
        return  std_id;
    }
    public int getBook_id() {
        return  book_id;
    }
    public String getAuthor() {
        return  author;
    }
    public String getBookName() {
        return  bookName;
    }
    public int getqnt() {
        return qnt;
    }
    public int getsrNo() {
        return srNo;
    }

}
