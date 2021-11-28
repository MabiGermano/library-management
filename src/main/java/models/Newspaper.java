package models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="TB_NEWSPAPER")
@DiscriminatorValue(value = "NEWSPAPER")
@PrimaryKeyJoinColumn(name="ID_MEDIA", referencedColumnName = "id")
public class Newspaper extends Media {

    @Column
    private String publishingCompany;
    @Column
    private Date releaseDate;
    @Column
    private String originState;

    public String getPublishingCompany() {
        return publishingCompany;
    }

    public void setPublishingCompany(String publishingCompany) {
        this.publishingCompany = publishingCompany;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getOriginState() {
        return originState;
    }

    public void setOriginState(String originState) {
        this.originState = originState;
    }
}
