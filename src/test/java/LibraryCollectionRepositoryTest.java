import models.Book;
import models.LibraryCollection;
import models.MediaBorrowing;
import models.Section;
import org.junit.Assert;
import org.junit.Test;
import repositories.BookRepository;
import repositories.LibraryCollectionRepository;
import repositories.MediaBorrowingRepository;
import repositories.SectionRepository;

import javax.persistence.CacheRetrieveMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNull;

public class LibraryCollectionRepositoryTest extends TestInitiator {

    @Test
    public void testingInsertLibraryCollection() {
        LibraryCollection newLibraryCollection = new LibraryCollection();
        newLibraryCollection.setName("Sessão Reservada");
        List<Section> sectionList = new ArrayList<Section>();
        sectionList.add(SectionRepository.findById(1L));
        newLibraryCollection.setSections(sectionList);

        LibraryCollection insertedLC = LibraryCollectionRepository.insertLibraryCollection(newLibraryCollection);
        Assert.assertNotNull(insertedLC.getId());
    }

    @Test
    public void testingFindLibraryCollection() {
        LibraryCollection libraryCollection = LibraryCollectionRepository.findById(1L);
        Assert.assertNotNull(libraryCollection);
        Assert.assertEquals("biblioteca multimidia", libraryCollection.getName());
    }

    @Test
    public void testingUpdateLibraryCollectionMerge() {
        String newName = "Novo nome";
        LibraryCollection libraryCollection = LibraryCollectionRepository.findById(1L);
        libraryCollection.setName(newName);
        em.clear();
        em.merge(libraryCollection);
        em.flush();
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        libraryCollection = LibraryCollectionRepository.findById(1L);
        Assert.assertEquals(newName, libraryCollection.getName());
    }

    @Test
    public void testingUpdateLibraryCollectionFlush() {
        String newName = "Novo nome";
        LibraryCollection libraryCollection = LibraryCollectionRepository.findById(1L);
        libraryCollection.setName(newName);
        em.flush();
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        libraryCollection = LibraryCollectionRepository.findById(1L);
        Assert.assertEquals(newName, libraryCollection.getName());
    }

    @Test
    public void removerLibraryCollection() {
        logger.info("Executando removerLibraryCollection()");
        LibraryCollection libraryCollection = LibraryCollectionRepository.findById(1L);
        em.remove(libraryCollection);
        em.flush();
        libraryCollection = LibraryCollectionRepository.findById(1L);
        assertNull(libraryCollection);
    }
}
