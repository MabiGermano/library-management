import models.DVD;
import models.Newspaper;
import org.junit.Assert;
import org.junit.Test;
import repositories.NewspaperRepository;

import java.util.Date;

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
        Assert.assertEquals("Festa de aniversário da cidade", newspaper.getTitle());
    }
}
