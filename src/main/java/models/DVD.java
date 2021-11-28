package models;

import javax.persistence.*;

@Entity
@Table(name="TB_DVD")
@DiscriminatorValue(value = "DVD")
@PrimaryKeyJoinColumn(name="ID_MEDIA", referencedColumnName = "id")
public class DVD extends Media {
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
}
