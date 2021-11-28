package models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "TB_MEDIA")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DISC_MEDIA",
        discriminatorType = DiscriminatorType.STRING, length = 1)
@Access(AccessType.FIELD)
public abstract class Media implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Column
    protected String title;
    @Column
    protected String genre;
    @Column
    protected String description;
    @Column
    protected int stockQuantity;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_media_borrowing", referencedColumnName = "id")
    protected MediaBorrowing mediaBorrowing;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_section", referencedColumnName = "id")
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
}
