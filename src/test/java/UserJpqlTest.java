import models.Section;
import models.User;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

public class UserJpqlTest extends TestInitiator{

    @Test
    public void userPeloZipCode(){
        logger.info("Executando userPeloZipCode()");
        String jpql = "SELECT u FROM User u join u.address ad where ad.zipCode = ?1";
        TypedQuery<User> query = em.createQuery(jpql, User.class);
        query.setParameter(1, "50690-220");
        List<User> users = query.getResultList();

        users.forEach(user -> Assert.assertEquals("50690-220", user.getAddress().getZipCode()));

        Assert.assertEquals(1, users.size());
    }
}
