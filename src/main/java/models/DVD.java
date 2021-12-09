package models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="TB_DVD")
@DiscriminatorValue(value = "DVD")
@PrimaryKeyJoinColumn(name="ID_DVD", referencedColumnName = "ID_MEDIA")
public class DVD extends Media implements Serializable {

    @Column(name="ARTIST")
    private String artist;
    @Column(name="DURATION")
    private int duration;
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
        return Objects.hash(super.hashCode(), artist, duration, yearOfDvd);
    }

    @Override
    public String toString() {
        return "DVD{" +
                "id=" + id +
                '}';
    }
}
