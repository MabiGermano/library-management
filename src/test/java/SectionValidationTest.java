import models.Author;
import models.Book;
import models.LibraryCollection;
import models.Section;
import org.junit.Assert;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SectionValidationTest extends TestInitiator {
    @Test(expected = ConstraintViolationException.class)
    public void persistInvalidSection() {

        Section newSection = new Section();
        try {
            newSection.setTitle("");
            newSection.setLibraryCollection(em.find(LibraryCollection.class, 1L));

            em.persist(newSection);
            em.flush();

        } catch (ConstraintViolationException ex) {
            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
            constraintViolations.stream().forEach(cv -> System.out.println(cv.getInvalidValue()));
            Assert.assertEquals(1, constraintViolations.size());
            Assert.assertNull(newSection.getId());
            throw ex;
        }
    }

    @Test(expected = ConstraintViolationException.class)
    public void updateInvalidSection() {
        Section section = em.find(Section.class, 2L);
        section.setTitle("");

        try {
            em.flush();
        } catch (ConstraintViolationException ex) {
            ConstraintViolation violation = ex.getConstraintViolations().iterator().next();

            Assert.assertEquals("Titulo não pode ser vazio", violation.getMessage());
            Assert.assertEquals(1, ex.getConstraintViolations().size());
            throw ex;
        }
    }
}
