import models.DVD;
import models.LibraryCollection;
import models.Section;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class LibraryCollectionValidationTest extends TestInitiator{
    @Test(expected = ConstraintViolationException.class)
    public void persistInvalidLibraryCollection() {

        LibraryCollection newLibraryCollection = new LibraryCollection();
        try {
            newLibraryCollection.setName("");
            newLibraryCollection.setSections(null);
            em.persist(newLibraryCollection);
            em.flush();

        } catch (ConstraintViolationException ex) {
            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
            constraintViolations.stream().forEach(cv -> System.out.println(cv.getInvalidValue()));
            Assert.assertEquals(2, constraintViolations.size());
            Assert.assertNull(newLibraryCollection.getId());
            throw ex;
        }
    }

    @Test(expected = ConstraintViolationException.class)
    public void updateInvalidLibraryCollection() {
        TypedQuery<LibraryCollection> query = em.createQuery("SELECT a FROM LibraryCollection a WHERE a.name like :name", LibraryCollection.class);
        query.setParameter("name", "biblioteca multimidia");
        LibraryCollection libraryCollection = query.getSingleResult();
        libraryCollection.setName("");

        try {
            em.flush();
        } catch (ConstraintViolationException ex) {
            ConstraintViolation violation = ex.getConstraintViolations().iterator().next();

            Assert.assertEquals("must not be blank", violation.getMessage());
            Assert.assertEquals(1, ex.getConstraintViolations().size());
            throw ex;
        }
    }
}
