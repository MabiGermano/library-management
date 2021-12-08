package models;

import javax.persistence.*;
import javax.print.attribute.standard.MediaSize;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "TB_SECTION")
public class Section implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "TITLE")
    private String title;
    @OneToMany(mappedBy = "section", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL, orphanRemoval = true)
    //TODO: verificar como colocar name aqui
    private HashSet<Media> medias;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_LIBRARY_COLLECTION", referencedColumnName = "ID")
    //TODO: verificar como colocar name aqui
    private LibraryCollection libraryCollection;

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

    public HashSet<Media> getMedias() {
        return medias;
    }

    public void setMedias(HashSet<Media> medias) {
        this.medias = medias;
    }

    public LibraryCollection getLibraryCollection() {
        return libraryCollection;
    }

    public void setLibraryCollection(LibraryCollection libraryCollection) {
        this.libraryCollection = libraryCollection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Section section = (Section) o;
        return Objects.equals(id, section.id) && Objects.equals(title, section.title) && Objects.equals(medias, section.medias) && Objects.equals(libraryCollection, section.libraryCollection);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, medias, libraryCollection);
    }

    @Override
    public String toString() {
        return "Section{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", medias=" + medias +
                ", libraryCollection=" + libraryCollection +
                '}';
    }
}
