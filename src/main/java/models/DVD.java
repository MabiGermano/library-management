package models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="TB_DVD")
@DiscriminatorValue(value = "dvd")
@PrimaryKeyJoinColumn(name="ID_MEDIA", referencedColumnName = "ID_MEDIA")
public class DVD extends Media implements Serializable {

    @NotBlank
    @Column(name="ARTIST")
    private String artist;
    @NotNull
    @Min(value = 1, message = "Duração mínima deve ser de 1")
    @Max(value = 600, message = "Duração máxima deve ser de 600")
    @Column(name="DURATION")
    private int duration;
    @NotBlank
    @Column(name = "YEAR_OF_DVD")
    private String yearOfDvd;

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getYearOfDvd() {
        return yearOfDvd;
    }

    public void setYearOfDvd(String yearOfDvd) {
        this.yearOfDvd = yearOfDvd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DVD dvd = (DVD) o;
        return duration == dvd.duration && artist.equals(dvd.artist) && yearOfDvd.equals(dvd.yearOfDvd);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
}
