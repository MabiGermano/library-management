package models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="TB_BOOK")
@DiscriminatorValue(value = "book")
@PrimaryKeyJoinColumn(name="ID_MEDIA", referencedColumnName = "ID_MEDIA")
public class Book extends Media implements Serializable {

    @Min(1)
    @Max(99999)
    @Column(name="TOTAL_PAGES")
    private int totalPages;
    @ManyToMany(mappedBy = "books")
    private List<Author> authors;
    @NotBlank
    @Column(name = "EDITION")
    private String edition;
    @NotBlank
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
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
}
