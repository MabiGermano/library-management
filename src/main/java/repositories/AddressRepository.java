package repositories;

import models.Address;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddressRepository {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("library-management");
    private static final Logger logger = Logger.getGlobal();

    static {
        logger.setLevel(Level.INFO);
    }

    public static void main(String[] args) {
        try {
            Address address = creatingAddress();
            Address find = findById(1L);
            System.out.println(find.getStreet());
        } finally {
            emf.close();
        }
    }

    private static Address findById(Long id) {
        EntityManager em = null;
        Address address = new Address();
        try {
            em = emf.createEntityManager();
            System.out.println("Getting Address from database...");
            address = em.find(Address.class, id);
            System.out.println(address.toString());
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return address;
    }

    public static Address insertAddress(Address newAddress) {

        EntityManager em = null;
        EntityTransaction et = null;
        try {
            em = emf.createEntityManager();
            et = em.getTransaction();
            et.begin();
            newAddress = em.merge(newAddress);
            et.commit();
        } catch (Exception ex) {
            if (et != null && et.isActive()) {
                logger.log(Level.SEVERE,
                        "[Canceling] Transaction with an error: {0}", ex.getMessage());
                et.rollback();
                logger.info("Canceled transaction");
            }
        } finally {
            if (em != null) {
                em.close();
            }
        }

        return newAddress;
    }

    public static Address creatingAddress() {
        Address newAddress = new Address();
        newAddress.setStreet("Rua Iolanda Rodrigues Sobral");
        newAddress.setZipCode("50690-220");
        newAddress.setCity("Recife");
        newAddress.setState("Pernambuco");
        newAddress.setNumber(550);

        newAddress = insertAddress(newAddress);
        return newAddress;
    }
}
