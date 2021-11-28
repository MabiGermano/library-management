package models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.UUID;

@Entity
@Table(name = "TB_USER")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String cpf;
    @Column
    private UUID registration;
    @Column
    private String tel;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private HashSet<MediaBorrowing> mediaBorrowings;
    @Column
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public UUID getRegistration() {
        return registration;
    }

    public void setRegistration(UUID registration) {
        this.registration = registration;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address adress) {
        this.address = adress;
    }

    public HashSet<MediaBorrowing> getMediaBorrowings() {
        return mediaBorrowings;
    }

    public void setMediaBorrowings(HashSet<MediaBorrowing> mediaBorrowings) {
        this.mediaBorrowings = mediaBorrowings;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
