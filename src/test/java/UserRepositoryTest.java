import models.User;
import org.junit.Assert;
import org.junit.Test;
import repositories.AddressRepository;
import repositories.UserRepository;

import javax.persistence.CacheRetrieveMode;
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
        newUser.setName("Usuário de teste");
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
        String newName = "Novo nome";
        User user = UserRepository.findById(1L);
        user.setName(newName);
        em.clear();
        em.merge(user);
        em.flush();
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        user = UserRepository.findById(1L);
        Assert.assertEquals(newName, user.getName());
    }

    @Test
    public void testingUpdateUserFlush() {
        String newName = "Novo nome";
        User user = UserRepository.findById(1L);
        user.setName(newName);
        em.flush();
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        user = UserRepository.findById(1L);
        Assert.assertEquals(newName, user.getName());
    }

    @Test
    public void removerUser() {
        logger.info("Executando removerUser()");
        User user = UserRepository.findById(1L);
        em.remove(user);
        em.flush();
        user = UserRepository.findById(1L);
        assertNull(user);
    }
}
