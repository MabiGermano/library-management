import models.Address;
import models.Author;
import models.Book;
import org.junit.Assert;
import org.junit.Test;
import repositories.BookRepository;


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
}
