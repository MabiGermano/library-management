import models.Address;
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

    @Test
    public void addressComNumeroMaiorQue30(){
        logger.info("Executando addressComNumeroMaiorQue30()");
        String jpql = "SELECT a FROM Address a where a.number > 30";
        TypedQuery<Address> query = em.createQuery(jpql, Address.class);
        List<Address> addresses = query.getResultList();

        Assert.assertEquals(2, addresses.size());
    }

    @Test
    public void addressComQuePertenceAPE(){
        logger.info("Executando addressComQuePertenceAPE()");
        String jpql = "SELECT a FROM Address a where a.state = 'PE'";
        TypedQuery<Address> query = em.createQuery(jpql, Address.class);
        List<Address> addresses = query.getResultList();

        Assert.assertEquals(2, addresses.size());
    }

    @Test
    public void addressComMediaBorrowingComDataMenorQue(){
        logger.info("Executando addressComQuePertenceAPE()");
        String jpql = "SELECT a FROM Address a inner join a.user u on a.user.id = u.id inner join u.mediaBorrowings m where m.createdAt < '2022-01-01'";
        TypedQuery<Address> query = em.createQuery(jpql, Address.class);
        List<Address> addresses = query.getResultList();

        Assert.assertEquals(1, addresses.size());
    }
}
