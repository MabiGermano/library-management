import models.*;
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
        newMediaBorrowing.setUser(em.find(User.class, 1L));
        newMediaBorrowing.setCreatedAt(new Date());
        newMediaBorrowing.setUpdatedAt(new Date());
        newMediaBorrowing.setBorrowed(true);
        HashSet<Media> mediasSet = new HashSet<Media>();
        mediasSet.add(em.find(Book.class, 1L));
        mediasSet.add(em.find(Book.class, 2L));
        mediasSet.add(em.find(DVD.class, 3L));
        newMediaBorrowing.setMedias(mediasSet);

        em.persist(newMediaBorrowing);
        em.flush();
        Assert.assertNotNull(newMediaBorrowing.getId());
    }

    @Test
    public void testingFindMediaBorrowing() {
        MediaBorrowing mediaBorrowing = em.find(MediaBorrowing.class, 1L);
        Assert.assertNotNull(mediaBorrowing);
        Assert.assertEquals(3, mediaBorrowing.getMedias().size());
    }

    @Test
    public void testingUpdateMediaBorrowingMerge() {
        Date newDate = new Date();
        MediaBorrowing mediaBorrowing = em.find(MediaBorrowing.class, 1L);
        mediaBorrowing.setUpdatedAt(newDate);
        em.clear();
        em.merge(mediaBorrowing);
        em.flush();
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        mediaBorrowing = em.find(MediaBorrowing.class, 1L);
        Assert.assertEquals(newDate, mediaBorrowing.getUpdatedAt());
    }

    @Test
    public void testingUpdateMediaBorrowingFlush() {
        Date newDate = new Date();
        MediaBorrowing mediaBorrowing = em.find(MediaBorrowing.class, 1L);
        mediaBorrowing.setUpdatedAt(newDate);
        em.flush();
        mediaBorrowing = em.find(MediaBorrowing.class, 1L);
        Assert.assertEquals(newDate, mediaBorrowing.getUpdatedAt());
    }

    @Test
    public void removerMediaBorrowing() {
        logger.info("Executando removerSection()");
        MediaBorrowing mediaBorrowing = em.find(MediaBorrowing.class, 2L);
        em.remove(mediaBorrowing);
        em.flush();
        mediaBorrowing = em.find(MediaBorrowing.class, 2L);
        assertNull(mediaBorrowing);
    }
}
