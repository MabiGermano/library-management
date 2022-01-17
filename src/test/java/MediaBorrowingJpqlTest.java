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
}
