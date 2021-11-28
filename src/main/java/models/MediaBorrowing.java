package models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;

@Entity
@Table(name = "TB_MEDIA_BORROWING")
public class MediaBorrowing implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private User user;
    @Column
    private boolean isBorrowed;
    @OneToMany(mappedBy = "mediaBorrowing", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private HashSet<Media> medias;
    @Column
    private Date createdAt;
    @Column
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
}
