package models;

import javax.persistence.*;

@Entity
@Table(name="TB_BOOK")
@DiscriminatorValue(value = "BOOK")
@PrimaryKeyJoinColumn(name="ID_MEDIA", referencedColumnName = "id")
public class Book extends Media{

    @Column
    private int totalPages;
    @Column
    private String author;
    @Column
    private String edition;
    @Column
    private String publishingCompany;

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getPublishingCompany() {
        return publishingCompany;
    }

    public void setPublishingCompany(String publishingCompany) {
        this.publishingCompany = publishingCompany;
    }
}
