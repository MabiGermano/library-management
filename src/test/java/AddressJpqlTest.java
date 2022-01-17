import models.Section;
import models.User;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

public class AddressJpqlTest extends TestInitiator{

    @Test
    public void addressLeftJoinUser(){
        logger.info("Executando addressLeftJoinUser()");
        String jpql = "SELECT a FROM Address a left join a.user u order by u.cpf";
        TypedQuery<User> query = em.createQuery(jpql, User.class);
        List<User> users = query.getResultList();

        Assert.assertEquals(2, users.size());
    }
}
