import models.*;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class MediaBorrowingValidationTest extends TestInitiator{
    @Test(expected = ConstraintViolationException.class)
    public void persistInvalidMediaBorrowing() {

        MediaBorrowing newMediaBorrowing = new MediaBorrowing();
        try {
            newMediaBorrowing.setUser(null);
            newMediaBorrowing.setBorrowed(true);

            em.persist(newMediaBorrowing);
            em.flush();

        } catch (ConstraintViolationException ex) {
            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
            constraintViolations.stream().forEach(cv -> System.out.println(cv.getInvalidValue()));
            Assert.assertEquals(1, constraintViolations.size());
            Assert.assertNull(newMediaBorrowing.getId());
            throw ex;
        }
    }

    @Test(expected = ConstraintViolationException.class)
    public void updateInvalidMediaBorrowing() {
        MediaBorrowing mediaBorrowing = em.find(MediaBorrowing.class, 1L);
        mediaBorrowing.setUser(null);

        try {
            em.flush();
        } catch (ConstraintViolationException ex) {
            ConstraintViolation violation = ex.getConstraintViolations().iterator().next();

            Assert.assertEquals("must not be null", violation.getMessage());
            Assert.assertEquals(1, ex.getConstraintViolations().size());
            throw ex;
        }
    }
}
