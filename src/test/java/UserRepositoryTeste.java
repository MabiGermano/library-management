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

public class UserRepositoryTeste {

    protected static EntityManagerFactory emf;

    @BeforeClass
    public static void setUpClass() {
        emf =Persistence.createEntityManagerFactory("library-management");

        DbUnitUtil.insertDefaultData();
    }

    @AfterClass
    public static void tearDownClass() {
        emf.close();
    }

    @Test
    public void testingUserInsert() {
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
}
