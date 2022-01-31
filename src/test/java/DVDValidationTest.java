import models.Book;
import models.DVD;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

public class DVDValidationTest extends TestInitiator{
    @Test(expected = ConstraintViolationException.class)
    public void persistInvalidDVD() {

            DVD newDvd = new DVD();
        try {
            newDvd.setTitle("Testando titulo enorme 1000 Testando titulo enorme 1000 Testando titulo enorme 1000 Testando titulo enorme 1000");
            newDvd.setDescription("");
            newDvd.setGenre("genero grande genero grande genero grande genero grande genero grande genero grande genero grande genero grande genero grande ");
            newDvd.setArtist("");
            newDvd.setYearOfDvd("");
            newDvd.setDuration(1000);

            em.persist(newDvd);
            em.flush();
        } catch (ConstraintViolationException ex) {
            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
            constraintViolations.stream().forEach(cv -> System.out.println(cv.getInvalidValue()));
            Assert.assertEquals(6, constraintViolations.size());
            Assert.assertNull(newDvd.getId());
            throw ex;
        }
    }

    @Test(expected = ConstraintViolationException.class)
    public void updateInvalidDVD() {
        TypedQuery<DVD> query = em.createQuery("SELECT a FROM DVD a WHERE a.title like :title", DVD.class);
        query.setParameter("title", "Homem-aranha");
        DVD dvd = query.getSingleResult();
        dvd.setDuration(99999);

        try {
            em.flush();
        } catch (ConstraintViolationException ex) {
            ConstraintViolation violation = ex.getConstraintViolations().iterator().next();

            Assert.assertEquals("must be less than or equal to 600", violation.getMessage());
            Assert.assertEquals(1, ex.getConstraintViolations().size());
            throw ex;
        }
    }
}
