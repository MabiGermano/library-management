import models.Address;
import models.User;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import repositories.AddressRepository;
import repositories.UserRepository;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.UUID;

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
}
