import models.Book;
import models.LibraryCollection;
import org.junit.Assert;
import org.junit.Test;
import repositories.BookRepository;

import javax.persistence.CacheRetrieveMode;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNull;


public class BookRepositoryTest extends TestInitiator{

    @Test
    public void testingInsertBook() {
        Book newBook = new Book();
        newBook.setTitle("Suicidas");
        newBook.setDescription("O primeiro romance do jovem autor que se firmou como principal nome do novo suspense brasileiro.\n" +
                "Do criador da série original Netflix \"Bom dia, Verônica\".");
        newBook.setGenre("Suspense");
        newBook.setEdition("1º");
        newBook.setPublishingCompany("Companhia das Letras");
        newBook.setTotalPages(342);

        em.persist(newBook);
        em.flush();
        Assert.assertNotNull(newBook.getId());
    }

    @Test
    public void testingFindBook() {
        Book book = em.find(Book.class, 1L);
        Assert.assertNotNull(book);
        Assert.assertEquals("A biblioteca da meia noite", book.getTitle());
    }

    @Test
    public void testingUpdateBookMerge() {
        int newNumber = 120;
        Book book = em.find(Book.class, 1L);
        book.setTotalPages(newNumber);
        em.clear();
        em.merge(book);
        em.flush();
        book = em.find(Book.class, 1L);
        Assert.assertEquals(newNumber, book.getTotalPages());
    }

    @Test
    public void testingUpdateBookFlush() {
        int newNumber = 20;
        Book book = em.find(Book.class, 1L);
        book.setTotalPages(newNumber);
        em.flush();
        book = em.find(Book.class, 1L);
        Assert.assertEquals(newNumber, book.getTotalPages());
    }

//    @Test
//    public void removerBook() {
//        logger.info("Executando removerBook()");
//        Book book = em.find(Book.class, 2L);
//        em.remove(book);
//        em.flush();
//        book = em.find(Book.class, 2L);
//        assertNull(book);
//    }
}
