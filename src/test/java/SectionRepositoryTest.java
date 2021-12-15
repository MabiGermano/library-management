import models.Book;
import models.Section;
import org.junit.Assert;
import org.junit.Test;
import repositories.BookRepository;
import repositories.LibraryCollectionRepository;
import repositories.SectionRepository;

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
}
