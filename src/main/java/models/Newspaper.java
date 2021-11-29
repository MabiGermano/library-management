package models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="TB_NEWSPAPER")
@DiscriminatorValue(value = "NEWSPAPER")
@PrimaryKeyJoinColumn(name="ID_MEDIA", referencedColumnName = "ID")
public class Newspaper extends Media implements Serializable {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Newspaper newspaper = (Newspaper) o;
        return publishingCompany.equals(newspaper.publishingCompany) && releaseDate.equals(newspaper.releaseDate) && originState.equals(newspaper.originState);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), publishingCompany, releaseDate, originState);
    }
}
