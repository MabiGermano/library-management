import models.Address;
import models.Book;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

public class BookValidationTest extends TestInitiator {
    @Test(expected = ConstraintViolationException.class)
    public void persistInvalidBook() {

        Book newBook = new Book();

        try {
            newBook.setTitle("Testando titulo maior que 100 Testando titulo maior que 100Testando titulo maior que 100 Testando titulo maior que 100Testando titulo maior que 100 Testando titulo maior que 100");
            newBook.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras iaculis gravida ligula. Nullam at aliquam dui. In turpis odio, consequat quis nisl ac, bibendum tempus urna. Nunc mattis tortor elit. Phasellus ac faucibus leo. Vestibulum mauris enim, pretium at faucibus et, iaculis sed sem. Praesent eu eleifend lacus. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.");
            newBook.setGenre("Testando titulo maior que 100 Testando titulo maior que 100um genero bem cumprido aqui um genero bem cumprido aqui um genero bem cumprido aqui ");
            newBook.setEdition("1º");
            newBook.setPublishingCompany("Companhia das Letras");
            newBook.setTotalPages(0);

            em.persist(newBook);
            em.flush();
        } catch (ConstraintViolationException ex) {
            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
            constraintViolations.stream().forEach(cv -> System.out.println(cv.getInvalidValue()));
            Assert.assertEquals(4, constraintViolations.size());
            Assert.assertNull(newBook.getId());
            throw ex;
        }
    }

    @Test(expected = ConstraintViolationException.class)
    public void updateInvalidBook() {
        TypedQuery<Book> query = em.createQuery("SELECT a FROM Book a WHERE a.title like :title", Book.class);
        query.setParameter("title", "Suicidas");
        Book book = query.getSingleResult();
        book.setTotalPages(12121221);

        try {
            em.flush();
        } catch (ConstraintViolationException ex) {
            ConstraintViolation violation = ex.getConstraintViolations().iterator().next();

            Assert.assertEquals("Páginas deve ter no máximo 99999", violation.getMessage());
            Assert.assertEquals(1, ex.getConstraintViolations().size());
            throw ex;
        }
    }
}