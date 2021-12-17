import models.*;
import org.junit.Assert;
import org.junit.Test;
import repositories.BookRepository;
import repositories.LibraryCollectionRepository;
import repositories.SectionRepository;
import repositories.UserRepository;

import javax.persistence.CacheRetrieveMode;
import javax.persistence.TypedQuery;
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
        String newTitle = "Novo nome";
        TypedQuery<Section> query = em.createNamedQuery("Section.ByTitle", Section.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter("title", "Algum titulo");
        Section section = query.getSingleResult();
        Assert.assertNotNull(section);
        section.setTitle(newTitle);
        em.clear();
        em.merge(section);
        em.flush();
        Assert.assertEquals(0, query.getResultList().size());
        query.setParameter("title", newTitle);
        section = query.getSingleResult();
        Assert.assertNotNull(section);
    }

    @Test
    public void testingUpdateSectionFlush() {
        String newTitle = "Novo nome";
        TypedQuery<Section> query = em.createNamedQuery("Section.ByTitle", Section.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter("title", "Algum titulo novo");
        Section section = query.getSingleResult();
        Assert.assertNotNull(section);
        section.setTitle(newTitle);
        em.flush();
        Assert.assertEquals(0, query.getResultList().size());
        query.setParameter("title", newTitle);
        section = query.getSingleResult();
        Assert.assertNotNull(section);
    }

    @Test
    public void removerSection() {
        logger.info("Executando removerSection()");
        TypedQuery<Section> query = em.createNamedQuery("Section.ByTitle", Section.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter("title", "Suspenses psicologicos");
        Section section = query.getSingleResult();
        Assert.assertNotNull(section);
        em.remove(section);
        em.flush();
        Assert.assertEquals(0, query.getResultList().size());
    }
}
