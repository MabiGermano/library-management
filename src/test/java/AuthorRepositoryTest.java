import models.Address;
import models.Author;
import models.Book;
import org.junit.Assert;
import org.junit.Test;
import repositories.AddressRepository;
import repositories.AuthorRepository;
import repositories.BookRepository;

import javax.persistence.CacheRetrieveMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class AuthorRepositoryTest extends TestInitiator{
    @Test
    public void testingInsertAuthor() {
        Author newAuthor = new Author();
        newAuthor.setName("Raphael Montes");
        List<Book> booksList = new ArrayList<Book>();
        booksList.add(BookRepository.findById(2L));
        booksList.add(BookRepository.findById(5L));
        newAuthor.setName("Raphael Montes");
        newAuthor.setBooks(booksList);

        Author insertedAuthor = AuthorRepository.insertAuthor(newAuthor);
        assertNotNull(insertedAuthor.getId());
    }

    @Test
    public void testingFindAuthor() {
        Author author = AuthorRepository.findById(1L);
        assertNotNull(author);
        Assert.assertEquals("Matt Haig", author.getName());
        Assert.assertEquals(1, author.getBooks().size());
    }

    @Test
    public void testingUpdateAuthorMerge() {
        String newName = "Outro author";
        Author author = AuthorRepository.findById(1L);
        assertNotNull(author);
        author.setName(newName);
        em.clear();
        em.merge(author);
        em.flush();
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        author = AuthorRepository.findById(1L);
        Assert.assertEquals(newName, author.getName());
    }

    @Test
    public void testingUpdateAuthorFlush() {
        String newName = "Outro author";
        Author author = AuthorRepository.findById(1L);
        assertNotNull(author);
        author.setName(newName);
        em.flush();
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        author = AuthorRepository.findById(1L);
        Assert.assertEquals(newName, author.getName());
    }

    @Test
    public void removerAuthor() {
        logger.info("Executando removerBook()");
        Author author = AuthorRepository.findById(1L);
        em.remove(author);
        em.flush();
        author = AuthorRepository.findById(1L);
        assertNull(author);
    }
}
