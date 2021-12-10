package models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "TB_USER")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "CPF")
    private String cpf;
    @Column(name = "REGISTRATION")
    private String registration;
    @Column(name = "TEL")
    private String tel;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_ADDRESS", referencedColumnName = "ID")
    //TODO: verificar como colocar name aqui
    private Address address;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    //TODO: verificar como colocar name aqui
    private HashSet<MediaBorrowing> mediaBorrowings;
    @Column(name = "EMAIL")
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

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(cpf, user.cpf) && Objects.equals(registration, user.registration) && Objects.equals(tel, user.tel) && Objects.equals(address, user.address) && Objects.equals(mediaBorrowings, user.mediaBorrowings) && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
}
