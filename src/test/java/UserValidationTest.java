import models.*;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.CacheRetrieveMode;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;
import java.util.UUID;

public class UserValidationTest extends TestInitiator{
    @Test(expected = ConstraintViolationException.class)
    public void persistInvalidUser() {

        User newUser = new User();
        try {
            newUser.setAddress(null);
            newUser.setCpf("90850704065");
            newUser.setEmail("testeteste.com.br");
            newUser.setName("Usuário de teste1");
            newUser.setRegistration(UUID.randomUUID().toString());
            newUser.setTel("0098811-6934");
            em.persist(newUser);
            em.flush();

        } catch (ConstraintViolationException ex) {
            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
            constraintViolations.stream().forEach(cv -> System.out.println(cv.getInvalidValue()));
            Assert.assertEquals(4, constraintViolations.size());
            Assert.assertNull(newUser.getId());
            throw ex;
        }
    }

    @Test
    public void updateInvalidUser(){
        TypedQuery<User> query = em.createQuery("SELECT a FROM User a WHERE a.name like :name", User.class);
        query.setParameter("name", "Usuário de teste");
        User user = query.getSingleResult();
        user.setName("");
        user.setTel("8198811-6934");

        ConstraintViolationException exception = Assert.assertThrows(ConstraintViolationException.class, () -> {
            em.flush();
        });

        Assert.assertEquals(3, exception.getConstraintViolations().size());
    }
}
