import models.Book;
import models.LibraryCollection;
import models.Section;
import models.User;
import org.junit.Assert;
import org.junit.Test;
import repositories.BookRepository;
import repositories.LibraryCollectionRepository;
import repositories.SectionRepository;
import repositories.UserRepository;

import javax.persistence.CacheRetrieveMode;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNull;

public class SectionRepositoryTest extends TestInitiator{
    @Test
    public void testingInsertSection() {
        Section newSection = new Section();
        newSection.setTitle("Suspenses psicologicos");
        newSection.setLibraryCollection(LibraryCollectionRepository.findById(1L));

        Section insertedSection = SectionRepository.insertSection(newSection);
        Assert.assertNotNull(insertedSection.getId());
    }

    @Test
    public void testingFindSection() {
        Section section = SectionRepository.findById(1L);
        Assert.assertNotNull(section);
        Assert.assertEquals("Algum titulo", section.getTitle());
    }

    @Test
    public void testingUpdateSectionMerge() {
        String newName = "Novo nome";
        Section section = SectionRepository.findById(1L);
        section.setTitle(newName);
        em.clear();
        em.merge(section);
        em.flush();
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        section = SectionRepository.findById(1L);
        Assert.assertEquals(newName, section.getTitle());
    }

    @Test
    public void testingUpdateSectionFlush() {
        String newName = "Novo nome";
        Section section = SectionRepository.findById(1L);
        section.setTitle(newName);
        em.flush();
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        section = SectionRepository.findById(1L);
        Assert.assertEquals(newName, section.getTitle());
    }

    @Test
    public void removerSection() {
        logger.info("Executando removerSection()");
        Section section = SectionRepository.findById(1L);
        em.remove(section);
        em.flush();
        section = SectionRepository.findById(1L);
        assertNull(section);
    }
}
