import models.Address;
import models.User;
import org.junit.Assert;
import org.junit.Test;
import repositories.AddressRepository;
import repositories.UserRepository;

import javax.persistence.CacheRetrieveMode;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.Assert.assertNull;

public class UserRepositoryTest extends TestInitiator {

    @Test
    public void testingInsertUser() {
        User newUser = new User();
        newUser.setAddress(AddressRepository.findById(1L));
        newUser.setCpf("908.507.040-65");
        newUser.setEmail("teste@teste.com.br");
        newUser.setName("Usuário de teste1");
        newUser.setRegistration(UUID.randomUUID().toString());
        newUser.setTel("(81) 98811-6934");

        User insertedUser = UserRepository.insertUser(newUser);
        Assert.assertNotNull(insertedUser.getId());
    }

    @Test
    public void testingFindUser() {
        User user = UserRepository.findById(1L);
        Assert.assertNotNull(user);
        Assert.assertEquals("8adab023-9691-4f32-8fb9-8db1fc84bd34", user.getRegistration());
    }

    @Test
    public void testingUpdateUserMerge() {
        TypedQuery<User> query = em.createNamedQuery("User.ByName", User.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter("name", "Usuário de teste2");
        User user = query.getSingleResult();
        Assert.assertNotNull(user);
        user.setName("Novo nome");
        em.clear();
        em.merge(user);
        em.flush();
        Assert.assertEquals(0, query.getResultList().size());
    }

    @Test
    public void testingUpdateUserFlush() {
        String newName = "Novo nome";
        TypedQuery<User> query = em.createNamedQuery("User.ByName", User.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter("name", "Usuário de teste");
        User user = query.getSingleResult();
        Assert.assertNotNull(user);
        user.setName(newName);
        em.flush();
        Assert.assertEquals(0, query.getResultList().size());
        query.setParameter("name", newName);
        user = query.getSingleResult();
        Assert.assertNotNull(user);
    }

    @Test
    public void removerUser() {
        TypedQuery<Address> query = em.createNamedQuery("User.ByName", Address.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter("name", "Usuário de teste1");
        Address address = query.getSingleResult();
        Assert.assertNotNull(address);
        em.remove(address);
        em.flush();
        Assert.assertEquals(0, query.getResultList().size());
    }
}
