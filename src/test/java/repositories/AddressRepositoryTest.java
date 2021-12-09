package repositories;

import models.Address;
import org.junit.Assert;
import org.junit.Test;

public class AddressRepositoryTest {

    @Test
    public void insertAddress() {
        Address newAddress = new Address();
        newAddress.setStreet("Rua Iolanda Rodrigues Sobral");
        newAddress.setZipCode("50690-220");
        newAddress.setCity("Recife");
        newAddress.setState("Pernambuco");
        newAddress.setNumber(550);

        Address insertedAddress = AddressRepository.insertAddress(newAddress);
        Assert.assertNotNull(insertedAddress.getId());
    }
}
