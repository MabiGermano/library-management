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

    @Test
    public void libraryCollectionComMaisDe3Sections(){
        logger.info("Executando libraryCollectionComMaisDe3Medias()");
        String jpql = "SELECT lb FROM LibraryCollection lb where (select distinct count(s) from Section s where s.libraryCollection.id = lb.id) > 3";
        TypedQuery<LibraryCollection> query = em.createQuery(jpql, LibraryCollection.class);
        List<LibraryCollection> libraryCollections = query.getResultList();

        Assert.assertEquals(0, libraryCollections.size());
    }

    @Test
    public void libraryCollectionComSectionEspecifica(){
        String title = "Algum titulo";
        logger.info("Executando libraryCollectionComSectionEspecifica()");
        String jpql = "SELECT lb FROM LibraryCollection lb inner join lb.sections s where s.title = ?1";
        TypedQuery<LibraryCollection> query = em.createQuery(jpql, LibraryCollection.class);
        query.setParameter(1, title);
        List<LibraryCollection> libraryCollections = query.getResultList();

        Assert.assertEquals(1, libraryCollections.size());
        int count = (int) libraryCollections.get(0).getSections().stream().filter(section -> section.getTitle().equals(title)).count();
        Assert.assertEquals(1, count);
    }
}
