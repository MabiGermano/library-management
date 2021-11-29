package models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="TB_DVD")
@DiscriminatorValue(value = "DVD")
@PrimaryKeyJoinColumn(name="ID_MEDIA", referencedColumnName = "ID")
public class DVD extends Media implements Serializable {

    @Column
    private String artist;
    @Column
    private int duration;
    @Column
    private String year;

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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DVD dvd = (DVD) o;
        return duration == dvd.duration && artist.equals(dvd.artist) && year.equals(dvd.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), artist, duration, year);
    }
}
