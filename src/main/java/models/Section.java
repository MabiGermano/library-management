package models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.UUID;

@Entity
@Table(name = "TB_SECTION")
public class Section implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String title;
    @OneToMany(mappedBy = "section", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private HashSet<Media> medias;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_LIBRARY_COLLECTION", referencedColumnName = "id")
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
}
