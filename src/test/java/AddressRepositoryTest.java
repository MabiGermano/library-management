import models.Address;
import models.Author;
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

        Address insertedAddress = AddressRepository.insertAddress(newAddress);
        Assert.assertNotNull(insertedAddress.getId());
    }

    @Test
    public void testingFindAddress() {
        Address address = AddressRepository.findById(1L);
        Assert.assertNotNull(address);
        Assert.assertEquals("50690-220", address.getZipCode());
    }

    @Test
    public void testingUpdateAddressMerge() {
        TypedQuery<Address> query = em.createNamedQuery("Address.ByStreet", Address.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter("street", "Rua dos bobos");
        Address address = query.getSingleResult();
        Assert.assertNotNull(address);
        address.setStreet("Rua dos bobinhos");
        em.clear();
        em.merge(address);
        em.flush();
        Assert.assertEquals(0, query.getResultList().size());
    }

    @Test
    public void testingUpdateAddressFlush() {

        TypedQuery<Address> query = em.createNamedQuery("Address.ByStreet", Address.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter("street", "Rua Iolanda Rodrigues Sobral");
        Address address = query.getSingleResult();
        Assert.assertNotNull(address);
        address.setStreet("Rua sem nome");
        em.flush();
        Assert.assertEquals(0, query.getResultList().size());
        query.setParameter("street", "Rua sem nome");
        address = query.getSingleResult();
        Assert.assertNotNull(address);
    }

    @Test
    public void removerAddress() {
        TypedQuery<Address> query = em.createNamedQuery("Address.ByStreet", Address.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter("street", "Rua Iolanda Rodrigues Sobral1");
        Address address = query.getSingleResult();
        Assert.assertNotNull(address);
        address.setStreet("Rua sem nome");
        em.remove(address);
        em.flush();
        Assert.assertEquals(0, query.getResultList().size());
    }
}
