import models.Address;
import org.dbunit.Assertion;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

public class AddressValidationTest extends TestInitiator{

    @Test(expected = ConstraintViolationException.class)
    public void persistInvalidAddress() {

            Address newAddress = new Address();

        try {
            newAddress.setStreet("");
            newAddress.setZipCode("50690220");
            newAddress.setCity("Recife");
            newAddress.setState("Pernambuco");
            newAddress.setNumber(10000);

            em.persist(newAddress);
            em.flush();
        } catch (ConstraintViolationException ex) {
            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();

            Assert.assertEquals(4, constraintViolations.size());
            Assert.assertNull(newAddress.getId());
            throw ex;
        }
    }

    @Test(expected = ConstraintViolationException.class)
    public void updateInvalidAddress() {
        TypedQuery<Address> query = em.createQuery("SELECT a FROM Address a WHERE a.zipCode like :zip_code", Address.class);
        query.setParameter("zip_code", "50690-232");
        Address address = query.getSingleResult();
        address.setState("Pernambuco");

        try {
            em.flush();
        } catch (ConstraintViolationException ex) {
            ConstraintViolation violation = ex.getConstraintViolations().iterator().next();

            Assert.assertEquals("size must be between 2 and 2", violation.getMessage());
            Assert.assertEquals(2, ex.getConstraintViolations().size());
            throw ex;
        }
    }
}
