import models.Address;
import models.Author;
import models.User;
import org.junit.Assert;
import org.junit.Test;
import repositories.AddressRepository;
import repositories.AuthorRepository;

import javax.persistence.CacheRetrieveMode;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNull;

public class AddressRepositoryTest extends TestInitiator{

    @Test
    public void testingInsertAddress() {
        Address newAddress = new Address();
        newAddress.setStreet("Rua Iolanda Rodrigues Sobral1");
        newAddress.setZipCode("50690-220");
        newAddress.setCity("Recife");
        newAddress.setState("Pernambuco");
        newAddress.setNumber(550);

        em.persist(newAddress);
        em.flush();
        Assert.assertNotNull(newAddress.getId());
    }

    @Test
    public void testingFindAddress() {
        Address address = em.find(Address.class, 1L);
        Assert.assertNotNull(address);
        Assert.assertEquals("50690-220", address.getZipCode());
    }

    @Test
    public void testingUpdateAddressMerge() {
        Address address = em.find(Address.class, 1L);
        address.setStreet("Rua dos bobinhos");
        em.clear();
        em.merge(address);
        em.flush();
        address = em.find(Address.class, 1L);
        Assert.assertEquals("Rua dos bobinhos", address.getStreet());
    }

    @Test
    public void testingUpdateAddressFlush() {
        Address address = em.find(Address.class, 1L);
        Assert.assertNotNull(address);
        address.setStreet("Rua sem nome");
        em.flush();
        address = em.find(Address.class, 1L);
        Assert.assertEquals("Rua sem nome", address.getStreet());
    }

    @Test
    public void removerAddress() {
        Address address = em.find(Address.class, 2L);
        Assert.assertNotNull(address);
        address.setStreet("Rua sem nome");
        em.remove(address);
        em.flush();
        address = em.find(Address.class, 2L);
        assertNull(address);
    }
}
