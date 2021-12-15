import models.LibraryCollection;
import models.Media;
import models.MediaBorrowing;
import models.Section;
import org.junit.Assert;
import org.junit.Test;
import repositories.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

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
}
