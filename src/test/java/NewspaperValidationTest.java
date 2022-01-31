import models.DVD;
import models.Newspaper;
import org.hibernate.validator.internal.util.privilegedactions.NewProxyInstance;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.Set;

public class NewspaperValidationTest extends TestInitiator{
    @Test(expected = ConstraintViolationException.class)
    public void persistInvalidNewspaper() {

        Newspaper newspaper = new Newspaper();
        try {
            newspaper.setTitle("Testando titulo grande Testando titulo grande Testando titulo grande Testando titulo grande Testando titulo grande Testando titulo grande Testando titulo grande ");
            newspaper.setDescription("");
            newspaper.setGenre("genero grande genero grande genero grande genero grande genero grande genero grande genero grande genero grande genero grande ");
            newspaper.setPublishingCompany("UOL");
            newspaper.setOriginState("");
            newspaper.setReleaseDate(new Date());

            em.persist(newspaper);
            em.flush();
        } catch (ConstraintViolationException ex) {
            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
            constraintViolations.stream().forEach(cv -> System.out.println(cv.getInvalidValue()));
            Assert.assertEquals(4, constraintViolations.size());
            Assert.assertNull(newspaper.getId());
            throw ex;
        }
    }

    @Test(expected = ConstraintViolationException.class)
    public void updateInvalidNewspaper() {
        TypedQuery<Newspaper> query = em.createQuery("SELECT a FROM Newspaper a WHERE a.title like :title", Newspaper.class);
        query.setParameter("title", "Festa de aniversário da cidade");
        Newspaper newspaper = query.getSingleResult();
        newspaper.setGenre("");

        try {
            em.flush();
        } catch (ConstraintViolationException ex) {
            ConstraintViolation violation = ex.getConstraintViolations().iterator().next();

            Assert.assertEquals("Genero não pode ser vazio", violation.getMessage());
            Assert.assertEquals(1, ex.getConstraintViolations().size());
            throw ex;
        }
    }
}
