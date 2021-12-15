import models.Book;
import models.DVD;
import org.junit.Assert;
import org.junit.Test;
import repositories.BookRepository;
import repositories.DVDRepository;

public class DVDRepositoryTest extends TestInitiator{
    @Test
    public void testingInsertDVD() {
        DVD newDvd = new DVD();
        newDvd.setTitle("Harry Potter e a Camara Secreta");
        newDvd.setDescription("Mais uma aventura do jovem Potter!!");
        newDvd.setGenre("Fantasia");
        newDvd.setArtist("Daniel Radicliffe");
        newDvd.setYearOfDvd("202");
        newDvd.setDuration(141);

        DVD insertedDvd = DVDRepository.insertDVD(newDvd);
        Assert.assertNotNull(insertedDvd.getId());
    }

    @Test
    public void testingFindDVD() {
        DVD dvd = DVDRepository.findById(3L);
        Assert.assertNotNull(dvd);
        Assert.assertEquals("Homem-aranha", dvd.getTitle());
    }
}
