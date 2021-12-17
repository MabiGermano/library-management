import models.LibraryCollection;
import models.Media;
import models.MediaBorrowing;
import models.Section;
import org.junit.Assert;
import org.junit.Test;
import repositories.*;

import javax.persistence.CacheRetrieveMode;
import java.util.*;

import static org.junit.Assert.assertNull;

public class MediaBorrowingRepositoryTest extends  TestInitiator{

    @Test
    public void testingInsertMediaBorrowing() {
        MediaBorrowing newMediaBorrowing = new MediaBorrowing();
        newMediaBorrowing.setUser(UserRepository.findById(1L));
        newMediaBorrowing.setCreatedAt(new Date());
        newMediaBorrowing.setUpdatedAt(new Date());
        newMediaBorrowing.setBorrowed(true);
        HashSet<Media> mediasSet = new HashSet<Media>();
        mediasSet.add(BookRepository.findById(1L));
        mediasSet.add(BookRepository.findById(2L));
        mediasSet.add(DVDRepository.findById(3L));
        newMediaBorrowing.setMedias(mediasSet);
        MediaBorrowing insertedMB = MediaBorrowingRepository.insertMediaBorrowing(newMediaBorrowing);
        Assert.assertNotNull(insertedMB.getId());
    }

    @Test
    public void testingFindMediaBorrowing() {
        MediaBorrowing mediaBorrowing = MediaBorrowingRepository.findById(1L);
        Assert.assertNotNull(mediaBorrowing);
        Assert.assertEquals(2, mediaBorrowing.getMedias().size());
    }

    @Test
    public void testingUpdateMediaBorrowingMerge() {
        Date newDate = new Date();
        MediaBorrowing mediaBorrowing = MediaBorrowingRepository.findById(1L);
        mediaBorrowing.setUpdatedAt(newDate);
        em.clear();
        em.merge(mediaBorrowing);
        em.flush();
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        mediaBorrowing = MediaBorrowingRepository.findById(1L);
        Assert.assertEquals(newDate, mediaBorrowing.getUpdatedAt());
    }

    @Test
    public void testingUpdateMediaBorrowingFlush() {
        Date newDate = new Date();
        MediaBorrowing mediaBorrowing = MediaBorrowingRepository.findById(1L);
        mediaBorrowing.setUpdatedAt(newDate);
        em.flush();
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        mediaBorrowing = MediaBorrowingRepository.findById(1L);
        Assert.assertEquals(newDate, mediaBorrowing.getUpdatedAt());
    }

    @Test
    public void removerMediaBorrowing() {
        logger.info("Executando removerSection()");
        MediaBorrowing mediaBorrowing = MediaBorrowingRepository.findById(1L);
        em.remove(mediaBorrowing);
        em.flush();
        mediaBorrowing = MediaBorrowingRepository.findById(1L);
        assertNull(mediaBorrowing);
    }
}
