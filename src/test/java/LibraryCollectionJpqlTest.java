import models.LibraryCollection;
import models.User;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

public class LibraryCollectionJpqlTest extends TestInitiator{

    @Test
    public void libraryCollectionComMaisDe3Medias(){
        logger.info("Executando libraryCollectionComMaisDe3Medias()");
        String jpql = "SELECT count(s) FROM LibraryCollection lb inner join lb.sections s join fetch s.medias m where m.stockQuantity > ?1";
        TypedQuery<Long> query = em.createQuery(jpql, Long.class);
        query.setParameter(1, 3);
        long count = query.getSingleResult();

        Assert.assertEquals(4l, count);
    }
}
