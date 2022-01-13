import models.DVD;
import models.Newspaper;
import org.junit.Assert;
import org.junit.Test;
import repositories.DVDRepository;
import repositories.NewspaperRepository;

import javax.persistence.CacheRetrieveMode;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class NewspaperRepositoryTest extends TestInitiator{
    @Test
    public void testingInsertNewspaper() {
        Newspaper newspaper = new Newspaper();
        newspaper.setTitle("HugoGloss");
        newspaper.setDescription("Coluna de fofocas upscale");
        newspaper.setGenre("Informativo");
        newspaper.setPublishingCompany("UOL");
        newspaper.setOriginState("São Paulo");
        newspaper.setReleaseDate(new Date());

        em.persist(newspaper);
        em.flush();
        Assert.assertNotNull(newspaper.getId());
    }

    @Test
    public void testingFindNewspaper() {
        String jpql = "SELECT n FROM Newspaper n WHERE n.id = ?1";
        TypedQuery<Newspaper> query = em.createQuery(jpql, Newspaper.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter(1, 4L);
        Newspaper newspaper = query.getSingleResult();
        Assert.assertNotNull(newspaper);
        assertEquals("Festa de aniversário da cidade", newspaper.getTitle());
    }

    @Test
    public void testingUpdateNewspaperMerge() {
        Date newDate = new Date();
        Newspaper newspaper = em.find(Newspaper.class, 4L);
        newspaper.setReleaseDate(newDate);
        em.clear();
        em.merge(newspaper);
        em.flush();
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        newspaper = em.find(Newspaper.class, 4L);
        assertEquals(newDate, newspaper.getReleaseDate());
    }

    @Test
    public void testingUpdateNewspaperFlush() {
        Date newDate = new Date();
        Newspaper newspaper = em.find(Newspaper.class, 4L);
        newspaper.setReleaseDate(newDate);
        em.flush();
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        newspaper = em.find(Newspaper.class, 4L);
        assertEquals(newDate, newspaper.getReleaseDate());
    }

    @Test
    public void removerNewspaper() {
        logger.info("Executando removerNewspaper()");
        Newspaper newspaper = em.find(Newspaper.class, 6L);
        em.remove(newspaper);
        em.flush();
        newspaper = em.find(Newspaper.class, 6L);
        assertNull(newspaper);
    }
}
