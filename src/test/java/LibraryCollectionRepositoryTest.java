import models.Book;
import models.LibraryCollection;
import models.Section;
import org.junit.Assert;
import org.junit.Test;
import repositories.BookRepository;
import repositories.LibraryCollectionRepository;
import repositories.SectionRepository;

import java.util.ArrayList;
import java.util.List;

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
}
