import models.Address;
import models.Author;
import org.junit.Assert;
import org.junit.Test;
import repositories.AddressRepository;
import repositories.AuthorRepository;

import javax.persistence.CacheRetrieveMode;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNull;

public class AddressRepositoryTest extends TestInitiator{

    @Test
    public void testingInsertAddress() {
        Address newAddress = new Address();
        newAddress.setStreet("Rua Iolanda Rodrigues Sobral");
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
        int newNumber = 20;
        Address address = AddressRepository.findById(1L);
        address.setNumber(newNumber);
        em.clear();
        em.merge(address);
        em.flush();
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        address = AddressRepository.findById(1L);
        Assert.assertEquals(newNumber, address.getNumber());
    }

    @Test
    public void testingUpdateAddressFlush() {
        int newNumber = 20;
        Address address = AddressRepository.findById(1L);
        address.setNumber(newNumber);
        em.flush();
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        address = AddressRepository.findById(1L);
        Assert.assertEquals(newNumber, address.getNumber());
    }

    @Test
    public void removerAddress() {
        logger.info("Executando removerAddress()");
        Address address = AddressRepository.findById(1L);
        em.remove(address);
        em.flush();
        address = AddressRepository.findById(1L);
        assertNull(address);
    }
}
