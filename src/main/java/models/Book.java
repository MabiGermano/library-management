package models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="TB_BOOK")
@DiscriminatorValue(value = "BOOK")
@PrimaryKeyJoinColumn(name="ID_MEDIA", referencedColumnName = "ID")
public class Book extends Media implements Serializable {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Book book = (Book) o;
        return totalPages == book.totalPages && author.equals(book.author) && edition.equals(book.edition) && publishingCompany.equals(book.publishingCompany);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), totalPages, author, edition, publishingCompany);
    }
}
