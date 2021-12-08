package models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "TB_LIBRARY_COLLECTION")
public class LibraryCollection implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "libraryCollection", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL, orphanRemoval = true)
    //TODO: como colocar name aqui
    private List<Section> sections = new ArrayList<Section>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LibraryCollection that = (LibraryCollection) o;
        return Objects.equals(id, that.id) && Objects.equals(sections, that.sections);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sections);
    }

    @Override
    public String toString() {
        return "LibraryCollection{" +
                "id=" + id +
                ", sections=" + sections +
                '}';
    }
}
