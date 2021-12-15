import models.Author;
import models.Book;
import org.junit.Assert;
import org.junit.Test;
import repositories.AuthorRepository;
import repositories.BookRepository;

import java.util.ArrayList;
import java.util.List;

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
        Assert.assertNotNull(insertedAuthor.getId());
    }

    @Test
    public void testingFindAuthor() {
        Author author = AuthorRepository.findById(1L);
        Assert.assertNotNull(author);
        Assert.assertEquals("Matt Haig", author.getName());
        Assert.assertEquals(1, author.getBooks().size());
    }
}
