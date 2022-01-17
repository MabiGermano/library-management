import models.Book;
import models.Media;
import models.Section;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

public class MediaJpqlTest extends TestInitiator {

    @Test
    public void livrosComMaisde300Paginas(){
        logger.info("Executando livrosComMaisde300Paginas()");
        String jpql = "SELECT b FROM Book b WHERE b.totalPages > ?1";
        TypedQuery<Book> query = em.createQuery(jpql, Book.class);
        query.setParameter(1, 300);
        List<Book> books = query.getResultList();

        Assert.assertEquals(3, books.size());
    }

    @Test
    public void MediasComMaisde5Paginas(){
        logger.info("Executando MediasComMaisde5Paginas()");
        String jpql = "SELECT m FROM Media m WHERE m.stockQuantity > ?1";
        TypedQuery<Media> query = em.createQuery(jpql, Media.class);
        query.setParameter(1, 5);
        List<Media> medias = query.getResultList();

        Assert.assertEquals(3, medias.size());
    }

    @Test
    public void MediasQuePertecemAoUsuario1(){
        logger.info("Executando MediasQuePertecemAoUsuario1()");
        String jpql = "SELECT m FROM Media m join m.mediaBorrowing mm join mm.user u where u.id = ?1";
        TypedQuery<Media> query = em.createQuery(jpql, Media.class);
        query.setParameter(1, 1L);
        List<Media> medias = query.getResultList();

        medias.forEach(media -> {
            Assert.assertEquals(1l, media.getMediaBorrowing().getUser().getId().longValue());

        });

        Assert.assertEquals(5, medias.size());
    }


    @Test
    public void MediasTituloComecaComF(){
        logger.info("Executando MediasComFiltroNoTitulo()");
        String jpql = "SELECT m FROM Media m WHERE m.title like :title";
        TypedQuery<Media> query = em.createQuery(jpql, Media.class);
        query.setParameter("title", "F%");
        List<Media> medias = query.getResultList();

        medias.forEach( media -> {
            Assert.assertTrue(media.getTitle().startsWith("F"));
        });

        Assert.assertEquals(2, medias.size());
    }

    @Test
    public void MediasTituloTemANoNome(){
        logger.info("Executando MediasComFiltroNoTitulo()");
        String jpql = "SELECT m FROM Media m WHERE m.title like :title";
        TypedQuery<Media> query = em.createQuery(jpql, Media.class);
        query.setParameter("title", "%a%");
        List<Media> medias = query.getResultList();

        medias.forEach( media -> {
            Assert.assertTrue(media.getTitle().contains("a"));
        });

        Assert.assertEquals(8, medias.size());
    }

}
