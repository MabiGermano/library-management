package models;

import models.validators.ValidState;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "TB_ADDRESS")
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(max = 150)
    @Column(name="STREET")
    private String street;
    @NotNull
    @Min(1)
    @Max(99999)
    @Column(name="NUMBER")
    private int number;
    @NotNull
    @Pattern(regexp = "[0-9]{5}-[0-9]{3}", message = "{models.Address.zipCode}")
    @Column(name="ZIP_CODE")
    private String zipCode;
    @NotBlank
    @Size(max = 150)
    @Column(name="CITY")
    private String city;
    @NotBlank
    @ValidState
    @Size(min = 2, max = 2)
    @Column(name="STATE")
    private String state;

    @OneToOne(mappedBy = "address", cascade = CascadeType.ALL)
    private User user;

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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return number == address.number && Objects.equals(id, address.id) && Objects.equals(street, address.street) && Objects.equals(zipCode, address.zipCode) && Objects.equals(city, address.city) && Objects.equals(state, address.state) && Objects.equals(user, address.user);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
}
