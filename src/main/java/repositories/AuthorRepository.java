package repositories;

import models.Address;
import models.Author;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AuthorRepository {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("library-management");
    private static final Logger logger = Logger.getGlobal();

    static {
        logger.setLevel(Level.INFO);
    }

    public static Author findById(Long id) {
        EntityManager em = null;
        Author author = null;
        try {
            em = emf.createEntityManager();
            System.out.println("Getting Author from database...");
            author = em.find(Author.class, id);
            System.out.println(author.toString());
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return author;
    }

    public static Author insertAuthor(Author newAuthor) {

        EntityManager em = null;
        EntityTransaction et = null;
        try {
            em = emf.createEntityManager();
            et = em.getTransaction();
            et.begin();
            newAuthor = em.merge(newAuthor);
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

        return newAuthor;
    }

//    public static void updateAddressWithMerge(Address newAddress) {
//
//        EntityManager em = null;
//        EntityTransaction et = null;
//        try {
//            em = emf.createEntityManager();
//            et = em.getTransaction();
//            et.begin();
//            em.clear();
//            em.merge(newAddress);
//            et.commit();
//        } catch (Exception ex) {
//            if (et != null && et.isActive()) {
//                logger.log(Level.SEVERE,
//                        "[Canceling] Transaction with an error: {0}", ex.getMessage());
//                et.rollback();
//                logger.info("Canceled transaction");
//            }
//        } finally {
//            if (em != null) {
//                em.close();
//            }
//        }
//    }
//
//    public static void updateAddressWithFlush(Address newAddress) {
//
//        EntityManager em = null;
//        EntityTransaction et = null;
//        try {
//            em = emf.createEntityManager();
//            et = em.getTransaction();
//            et.begin();
//            em.flush();
//            et.commit();
//        } catch (Exception ex) {
//            if (et != null && et.isActive()) {
//                logger.log(Level.SEVERE,
//                        "[Canceling] Transaction with an error: {0}", ex.getMessage());
//                et.rollback();
//                logger.info("Canceled transaction");
//            }
//        } finally {
//            if (em != null) {
//                em.close();
//            }
//        }
//    }
}
