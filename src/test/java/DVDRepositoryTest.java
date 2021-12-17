import models.Book;
import models.DVD;
import models.LibraryCollection;
import org.junit.Assert;
import org.junit.Test;
import repositories.BookRepository;
import repositories.DVDRepository;
import repositories.LibraryCollectionRepository;

import javax.persistence.CacheRetrieveMode;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNull;

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

    @Test
    public void testingUpdateDVDMerge() {
        String newYear = "2020";
        DVD dvd = DVDRepository.findById(1L);
        dvd.setYearOfDvd(newYear);
        em.clear();
        em.merge(dvd);
        em.flush();
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        dvd = DVDRepository.findById(1L);
        Assert.assertEquals(newYear, dvd.getYearOfDvd());
    }

    @Test
    public void testingUpdateDVDFlush() {
        String newYear = "2020";
        DVD dvd = DVDRepository.findById(1L);
        dvd.setYearOfDvd(newYear);
        em.flush();
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        dvd = DVDRepository.findById(1L);
        Assert.assertEquals(newYear, dvd.getYearOfDvd());
    }

    @Test
    public void removerDVD() {
        logger.info("Executando removerDVD()");
        DVD dvd = DVDRepository.findById(1L);
        em.remove(dvd);
        em.flush();
        dvd = DVDRepository.findById(1L);
        assertNull(dvd);
    }
}
