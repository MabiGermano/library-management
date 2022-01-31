import models.LibraryCollection;
import models.Media;
import models.MediaBorrowing;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MediaBorrowingJpqlTest extends TestInitiator{

    @Test
    public void maximaEMinimaDataCriacao(){
        logger.info("Executando maximaEMinimaDataCriacao()");
        String jpql = "SELECT MAX(mb.createdAt), MIN(mb.createdAt) FROM MediaBorrowing mb";
        Query query = em.createQuery(jpql);
        Object[] resultado = (Object[]) query.getSingleResult();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String maiorData = dateFormat.format((Date) resultado[0]);
        String menorData = dateFormat.format((Date) resultado[1]);
        assertEquals("03-05-2022", maiorData);
        assertEquals("08-12-2021", menorData);
    }

    @Test
    public void mediaBorrowingComBorrowedTrue(){
        logger.info("Executando mediaBorrowingComBorrowedTrue()");
        String jpql = "SELECT mb FROM MediaBorrowing mb where mb.isBorrowed = true";
        TypedQuery<MediaBorrowing> query = em.createQuery(jpql, MediaBorrowing.class);
        List<MediaBorrowing> mediaBorrowings = query.getResultList();

        assertEquals(3, mediaBorrowings.size());
        mediaBorrowings.forEach(mediaBorrowing -> {
            assertTrue(mediaBorrowing.isBorrowed());
        });
    }

    @Test
    public void mediaBorrowingComMediasDeDeterminadoGenero(){
        String genre = "Drama";
        logger.info("Executando mediaBorrowingComMediasDeDeterminadoGenero()");
        String jpql = "SELECT mb FROM MediaBorrowing mb inner join mb.medias m where m.genre = ?1";
        TypedQuery<MediaBorrowing> query = em.createQuery(jpql, MediaBorrowing.class);
        query.setParameter(1, genre);
        List<MediaBorrowing> mediaBorrowings = query.getResultList();

        assertEquals(1, mediaBorrowings.size());

        int count = (int) mediaBorrowings.get(0).getMedias().stream().filter(media -> media.getGenre().equals(genre)).count();
        Assert.assertEquals(1, count);
    }
}
