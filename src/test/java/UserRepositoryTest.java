import models.Address;
import models.User;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.CacheRetrieveMode;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.Assert.assertNull;

public class UserRepositoryTest extends TestInitiator {

    @Test
    public void testingInsertUser() {
        String jpql = "SELECT a FROM Address a WHERE a.id = ?1";
        TypedQuery<Address> query = em.createQuery(jpql, Address.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter(1, 2L);
        Address address = query.getSingleResult();
        User newUser = new User();
        newUser.setAddress(address);
        newUser.setCpf("908.507.040-65");
        newUser.setEmail("teste@teste.com.br");
        newUser.setName("Usuário de teste1");
        newUser.setRegistration(UUID.randomUUID().toString());
        newUser.setTel("(81) 98811-6934");

        em.persist(newUser);
        em.flush();
        Assert.assertNotNull(newUser.getId());
    }

    @Test
    public void testingFindUser() {
        String jpql = "SELECT u FROM User u WHERE u.id = ?1";
        TypedQuery<User> query = em.createQuery(jpql, User.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter(1, 2L);
        User user = query.getSingleResult();
        Assert.assertNotNull(user);
        Assert.assertEquals("8p5ab023-9oc1-4f72-8fb9-88bgfc848d34", user.getRegistration());
    }

    @Test
    public void testingUpdateUserMerge() {
        String jpql = "SELECT u FROM User u WHERE u.id = ?1";
        TypedQuery<User> query = em.createQuery(jpql, User.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter(1, 2L);
        User user = query.getSingleResult();
        Assert.assertNotNull(user);
        user.setName("Novo nomezinho");
        em.clear();
        em.merge(user);
        em.flush();
    }

    @Test
    public void testingUpdateUserFlush() {
        String newName = "Novo nome";
        String jpql = "SELECT u FROM User u WHERE u.name = ?1";
        TypedQuery<User> query = em.createQuery(jpql, User.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter(1, "Usuário de teste2");
        User user = query.getSingleResult();
        Assert.assertNotNull(user);
        user.setName(newName);
        em.flush();
        Assert.assertEquals(0, query.getResultList().size());
        query.setParameter(1, newName);
        user = query.getSingleResult();
        Assert.assertNotNull(user);
    }

    @Test
    public void removerUser() {
        String jpql = "SELECT u FROM User u WHERE u.name = ?1";
        TypedQuery<User> query = em.createQuery(jpql, User.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter(1, "Usuário de teste");
        User user = query.getSingleResult();
        Assert.assertNotNull(user);
        em.remove(user);
        User removedUser = em.find(User.class, user.getId());
        Assert.assertNull(removedUser);
    }
}
