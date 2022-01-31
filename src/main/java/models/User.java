package models;

import org.eclipse.persistence.annotations.CascadeOnDelete;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
    @NotBlank(message = "Nome do usuário não deve ser branco")
    @Column(name = "NAME")
    private String name;
    @CPF (message = "O formato do cpf deve ser xxx.xxx.xxx-xx")
    @Column(name = "CPF")
    private String cpf;
    @Column(name = "REGISTRATION")
    private String registration;
    @NotNull
    @Pattern(regexp = "([0-9]{2}) [0-9]{5}-[0-9]{4}", message = "O formato do numero de telefone deve ser (xx) xxxxx-xxxx")
    @Column(name = "TEL")
    private String tel;
    @NotNull
    @Valid
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ID_ADDRESS", referencedColumnName = "ID")
    private Address address;
    @NotNull
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private HashSet<MediaBorrowing> mediaBorrowings;
    @NotNull
    @Email
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
