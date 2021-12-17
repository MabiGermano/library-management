import models.Book;
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

        Book insertedBook = BookRepository.insertBook(newBook);
        Assert.assertNotNull(insertedBook.getId());
    }

    @Test
    public void testingFindBook() {
        Book book = BookRepository.findById(1L);
        Assert.assertNotNull(book);
        Assert.assertEquals("A biblioteca da meia noite", book.getTitle());
    }

    @Test
    public void testingUpdateBookMerge() {
        int newNumber = 120;
        Book book = BookRepository.findById(1L);
        book.setTotalPages(newNumber);
        em.clear();
        em.merge(book);
        em.flush();
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        book = BookRepository.findById(1L);
        Assert.assertEquals(newNumber, book.getTotalPages());
    }

    @Test
    public void testingUpdateBookFlush() {
        int newNumber = 20;
        Book book = BookRepository.findById(1L);
        book.setTotalPages(newNumber);
        em.flush();
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        book = BookRepository.findById(1L);
        Assert.assertEquals(newNumber, book.getTotalPages());
    }

    @Test
    public void removerBook() {
        logger.info("Executando removerBook()");
        Book book = BookRepository.findById(1L);
        em.remove(book);
        em.flush();
        book = BookRepository.findById(1L);
        assertNull(book);
    }
}
