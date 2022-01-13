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
        newSection.setLibraryCollection(em.find(LibraryCollection.class, 1L));

        em.persist(newSection);
        em.flush();
        Assert.assertNotNull(newSection.getId());
    }

    @Test
    public void testingFindSection() {
        Section section = em.find(Section.class, 1L);
        Assert.assertNotNull(section);
        Assert.assertEquals("Algum titulo", section.getTitle());
    }

    @Test
    public void testingUpdateSectionMerge() {
        String newTitle = "Novo nome merge";
        String jpql = "SELECT s FROM Section s WHERE s.title = ?1";
        TypedQuery<Section> query = em.createQuery(jpql, Section.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter(1, "Novo nome");
        Section section = query.getSingleResult();
        Assert.assertNotNull(section);
        section.setTitle(newTitle);
        em.clear();
        em.merge(section);
        em.flush();
        Assert.assertEquals(0, query.getResultList().size());
        query.setParameter(1, newTitle);
        section = query.getSingleResult();
        Assert.assertNotNull(section);
    }

    @Test
    public void testingUpdateSectionFlush() {
        String newTitle = "Novo nome";
        String jpql = "SELECT s FROM Section s WHERE s.title = ?1";
        TypedQuery<Section> query = em.createQuery(jpql, Section.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter(1, "Algum titulo");
        Section section = query.getSingleResult();
        Assert.assertNotNull(section);
        section.setTitle(newTitle);
        em.flush();
        Assert.assertEquals(0, query.getResultList().size());
        query.setParameter(1, newTitle);
        section = query.getSingleResult();
        Assert.assertNotNull(section);
    }

    @Test
    public void removerSection() {
        logger.info("Executando removerSection()");
        String jpql = "SELECT s FROM Section s WHERE s.title = ?1";
        TypedQuery<Section> query = em.createQuery(jpql, Section.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter(1, "Algum titulo novo");
        Section section = query.getSingleResult();
        Assert.assertNotNull(section);
        em.remove(section);
        em.flush();
        Assert.assertEquals(0, query.getResultList().size());
    }
}
