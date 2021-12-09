package models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "TB_MEDIA")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "DISC_MEDIA",
        discriminatorType = DiscriminatorType.STRING, length = 10)
public abstract class Media implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Column(name = "TITLE")
    protected String title;
    @Column(name = "GENRE")
    protected String genre;
    @Column(name = "DESCRIPTION")
    protected String description;
    @Column(name = "STOCK_QUALITY")
    protected int stockQuantity;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_MEDIA_BORROWING", referencedColumnName = "ID")
    //TODO: verificar como colocar name aqui
    protected MediaBorrowing mediaBorrowing;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_SECTION", referencedColumnName = "ID")
    //TODO: verificar como colocar name aqui
    protected Section section;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public MediaBorrowing getMediaBorrowing() {
        return mediaBorrowing;
    }

    public void setMediaBorrowing(MediaBorrowing mediaBorrowing) {
        this.mediaBorrowing = mediaBorrowing;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Media media = (Media) o;
        return stockQuantity == media.stockQuantity && Objects.equals(id, media.id) && Objects.equals(title, media.title) && Objects.equals(genre, media.genre) && Objects.equals(description, media.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, genre, description, stockQuantity);
    }

    @Override
    public String toString() {
        return "Media{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", description='" + description + '\'' +
                ", stockQuantity=" + stockQuantity +
                ", mediaBorrowing=" + mediaBorrowing +
                ", section=" + section +
                '}';
    }
}
