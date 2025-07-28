package dto;

public class Book {
    private int id;
    private String bookName;
    private String authorName;
    private int srNo;
    private int bookQty;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setbookQty(int bookQty) {
        this.bookQty = bookQty;
    }

    public int getbookQty() {
        return this.bookQty;
    }

    public void setbookName(String bookName) {
        this.bookName = bookName;
    }

    public String getbookName() {
        return this.bookName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorName() {
        return this.authorName;
    }

    public void setSrNo(int srNo) {
        this.srNo = srNo;
    }

    public int getsrNo() {
        return this.srNo;
    }
}
