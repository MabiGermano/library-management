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
        newAuthor.setName("Raphael Monteiro");
        List<Book> booksList = new ArrayList<Book>();
        booksList.add(em.find(Book.class, 2L));
        booksList.add(em.find(Book.class, 5L));

        em.persist(newAuthor);
        em.flush();
        assertNotNull(newAuthor.getId());
    }

    @Test
    public void testingFindAuthor() {
        Author author = em.find(Author.class, 2L);
        assertNotNull(author);
        Assert.assertEquals("Raphael Montes", author.getName());
        Assert.assertEquals(1, author.getBooks().size());
    }

    @Test
    public void testingUpdateAuthorMerge() {
        String newName = "Outro author";
        Author author = em.find(Author.class, 2L);
        assertNotNull(author);
        author.setName(newName);
        em.clear();
        em.merge(author);
        em.flush();
        author = em.find(Author.class, 2L);
        Assert.assertEquals(newName, author.getName());
    }

    @Test
    public void testingUpdateAuthorFlush() {
        String newName = "Outro author";
        Author author = em.find(Author.class, 2L);
        assertNotNull(author);
        author.setName(newName);
        em.flush();
        author = em.find(Author.class, 2L);
        Assert.assertEquals(newName, author.getName());
    }

    @Test
    public void removerAuthor() {
        logger.info("Executando removerBook()");
        Author author = em.find(Author.class, 1L);
        em.remove(author);
        em.flush();
        author = em.find(Author.class, 1L);
        assertNull(author);
    }
}
