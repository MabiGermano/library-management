import models.Book;
import models.DVD;
import models.LibraryCollection;
import models.User;
import org.junit.Assert;
import org.junit.Test;
import repositories.BookRepository;
import repositories.DVDRepository;
import repositories.LibraryCollectionRepository;

import javax.persistence.CacheRetrieveMode;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
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

        em.persist(newDvd);
        em.flush();
        Assert.assertNotNull(newDvd.getId());
    }

    @Test
    public void testingFindDVD() {
        String jpql = "SELECT d FROM DVD d WHERE d.id = ?1";
        TypedQuery<DVD> query = em.createQuery(jpql, DVD.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter(1, 3L);
        DVD dvd = query.getSingleResult();
        Assert.assertNotNull(dvd);
        Assert.assertEquals("Homem-aranha", dvd.getTitle());
    }

    @Test
    public void testingUpdateDVDMerge() {
        String newYear = "2020";
        DVD dvd = em.find(DVD.class, 3L);
        dvd.setYearOfDvd(newYear);
        em.clear();
        em.merge(dvd);
        em.flush();
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        dvd = em.find(DVD.class, 3L);
        Assert.assertEquals(newYear, dvd.getYearOfDvd());
    }

    @Test
    public void testingUpdateDVDFlush() {
        String newYear = "2020";
        DVD dvd = em.find(DVD.class, 3L);
        dvd.setYearOfDvd(newYear);
        em.flush();
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        dvd = em.find(DVD.class, 3L);
        Assert.assertEquals(newYear, dvd.getYearOfDvd());
    }

    @Test
    public void removerDVD() {
        logger.info("Executando removerDVD()");
        DVD dvd = em.find(DVD.class, 6L);
        em.remove(dvd);
        em.flush();
        dvd = em.find(DVD.class, 6L);
        assertNull(dvd);
    }
}
