package models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="TB_BOOK")
@DiscriminatorValue(value = "BOOK")
@PrimaryKeyJoinColumn(name="ID_BOOK", referencedColumnName = "ID_MEDIA")
public class Book extends Media implements Serializable {

    @Column(name="TOTAL_PAGES")
    private int totalPages;
//    @ManyToMany(mappedBy = "books")
    // TODO: verificar como coloca name aqui
//    private List<Author> authors;
    @Column(name = "EDITION")
    private String edition;
    @Column(name="PUBLISHING_COMPANY")
    private String publishingCompany;

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

//    public List<Author> getAuthors() {
//        return authors;
//    }
//
//    public void setAuthors(List<Author> authors) {
//        this.authors = authors;
//    }

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
        return totalPages == book.totalPages && Objects.equals(edition, book.edition) && Objects.equals(publishingCompany, book.publishingCompany);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), totalPages, edition, publishingCompany);
    }

    @Override
    public String toString() {
        return "Book{" +
                "totalPages=" + totalPages +
                ", edition='" + edition + '\'' +
                ", publishingCompany='" + publishingCompany + '\'' +
                '}';
    }
}
