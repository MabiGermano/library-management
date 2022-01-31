package models;

import javax.persistence.*;
import javax.print.attribute.standard.MediaSize;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    @NotBlank(message = "Titulo não pode ser vazio")
    @Size(max = 100)
    @Column(name = "TITLE")
    private String title;
    @OneToMany(mappedBy = "section", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private HashSet<Media> medias;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_LIBRARY_COLLECTION", referencedColumnName = "ID")
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
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
}
