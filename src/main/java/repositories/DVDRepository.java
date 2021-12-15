package repositories;

import models.Address;
import models.DVD;
import models.Media;
import models.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DVDRepository {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("library-management");
    private static final Logger logger = Logger.getGlobal();

    static {
        logger.setLevel(Level.INFO);
    }

    public static void main(String[] args) {
        try {
            DVD dvd = creatingDVD();
            DVD find = findById(1L);
            System.out.println("ID Persist: " + dvd.getId());
            System.out.println("ID Find: " + find.getId());
        } finally {
            emf.close();
        }
    }

    public static DVD findById(Long id) {
        EntityManager em = null;
        DVD dvd = new DVD();
        try {
            em = emf.createEntityManager();
            System.out.println("Getting DVD from database...");
            dvd = em.find(DVD.class, id);
            System.out.println(dvd.toString());
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return dvd;
    }

    public static DVD insertDVD(DVD dvd){
        EntityManager em = null;
        EntityTransaction et = null;
        try {
            em = emf.createEntityManager();
            et = em.getTransaction();
            et.begin();
            dvd = em.merge(dvd);
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

        return dvd;
    }

    public static DVD creatingDVD(){
        DVD dvd = new DVD();
        dvd.setTitle("Harry Potter e a Pedra Filosofal");
        dvd.setArtist("Steven Kloves");
        dvd.setDuration(152);
        dvd.setYearOfDvd("2001");
        dvd.setDescription("Harry Potter é um garoto órfão");
        dvd.setGenre("Fantasia");
        dvd.setStockQuantity(3);

        dvd = insertDVD(dvd);

        return dvd;
    }
}
