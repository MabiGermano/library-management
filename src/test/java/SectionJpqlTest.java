import models.Section;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

public class SectionJpqlTest extends TestInitiator{

    @Test
    public void sectionComMaisDe3Medias(){
        logger.info("Executando sectionComMaisDe3Medias()");
        String jpql = "SELECT s FROM Section s WHERE (SELECT COUNT(m) FROM Media m where m MEMBER OF s.medias) > ?1";
        TypedQuery<Section> query = em.createQuery(jpql, Section.class);
        query.setParameter(1, 3);
        List<Section> sections = query.getResultList();

        Assert.assertEquals(1, sections.size());
    }

    @Test
    public void sectionComJoinParaLibraryCollection(){
        logger.info("Executando sectionComJoinParaLibraryCollection()");
        String jpql = "SELECT s FROM Section s join s.libraryCollection lb where lb.name = ?1";
        TypedQuery<Section> query = em.createQuery(jpql, Section.class);
        query.setParameter(1, "biblioteca multimidia");
        List<Section> sections = query.getResultList();

        Assert.assertEquals(2, sections.size());
    }

    @Test
    public void sectionComJoinParaMediaComMaisde5NoStock(){
        logger.info("Executando sectionComJoinParaMediaComMaisde5NoStock()");
        String jpql = "SELECT s FROM Section s join fetch s.medias m where m.stockQuantity > ?1";
        TypedQuery<Section> query = em.createQuery(jpql, Section.class);
        query.setParameter(1, 5);
        List<Section> sections = query.getResultList();

        Assert.assertEquals(3, sections.size());
    }
}
