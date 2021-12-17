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

        Newspaper insertedNewspaper = NewspaperRepository.insertNewspaper(newspaper);
        Assert.assertNotNull(insertedNewspaper.getId());
    }

    @Test
    public void testingFindNewspaper() {
        Newspaper newspaper = NewspaperRepository.findById(4L);
        Assert.assertNotNull(newspaper);
        assertEquals("Festa de aniversário da cidade", newspaper.getTitle());
    }

    @Test
    public void testingUpdateNewspaperMerge() {
        Date newDate = new Date();
        Newspaper newspaper = NewspaperRepository.findById(1L);
        newspaper.setReleaseDate(newDate);
        em.clear();
        em.merge(newspaper);
        em.flush();
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        newspaper = NewspaperRepository.findById(1L);
        assertEquals(newDate, newspaper.getReleaseDate());
    }

    @Test
    public void testingUpdateNewspaperFlush() {
        Date newDate = new Date();
        Newspaper newspaper = NewspaperRepository.findById(1L);
        newspaper.setReleaseDate(newDate);
        em.flush();
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        newspaper = NewspaperRepository.findById(1L);
        assertEquals(newDate, newspaper.getReleaseDate());
    }

    @Test
    public void removerNewspaper() {
        logger.info("Executando removerNewspaper()");
        Newspaper newspaper = NewspaperRepository.findById(1L);
        em.remove(newspaper);
        em.flush();
        newspaper = NewspaperRepository.findById(1L);
        assertNull(newspaper);
    }
}
