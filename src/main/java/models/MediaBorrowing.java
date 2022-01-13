package models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;

@Entity
@Table(name = "TB_MEDIA_BORROWING")
public class MediaBorrowing implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_USER", referencedColumnName = "ID")
    private User user;
    @Column(name = "IS_BORROWED")
    private boolean isBorrowed;
    @OneToMany(mappedBy = "mediaBorrowing", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private HashSet<Media> medias;
    @Column(name = "CREATED_AT")
    private Date createdAt;
    @Column(name = "UPDATED_AT")
    private Date updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }

    public HashSet<Media> getMedias() {
        return medias;
    }

    public void setMedias(HashSet<Media> medias) {
        this.medias = medias;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MediaBorrowing that = (MediaBorrowing) o;
        return isBorrowed == that.isBorrowed && Objects.equals(id, that.id) && Objects.equals(user, that.user) && Objects.equals(medias, that.medias) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
}
