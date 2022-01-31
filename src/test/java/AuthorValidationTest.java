import models.Author;
import models.Book;
import models.DVD;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AuthorValidationTest extends  TestInitiator{
    @Test(expected = ConstraintViolationException.class)
    public void persistInvalidAuthor() {

        Author newAuthor = new Author();
        try {
            newAuthor.setName("");
            List<Book> booksList = new ArrayList<Book>();
            booksList.add(em.find(Book.class, 2L));
            booksList.add(em.find(Book.class, 5L));

            em.persist(newAuthor);
            em.flush();

        } catch (ConstraintViolationException ex) {
            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
            constraintViolations.stream().forEach(cv -> System.out.println(cv.getInvalidValue()));
            Assert.assertEquals(1, constraintViolations.size());
            Assert.assertNull(newAuthor.getId());
            throw ex;
        }
    }

    @Test(expected = ConstraintViolationException.class)
    public void updateInvalidAuthor() {
        Author author = em.find(Author.class, 2L);
        author.setName("");

        try {
            em.flush();
        } catch (ConstraintViolationException ex) {
            ConstraintViolation violation = ex.getConstraintViolations().iterator().next();

            Assert.assertEquals("Nome não deve ser vazio", violation.getMessage());
            throw ex;
        }
    }
}
