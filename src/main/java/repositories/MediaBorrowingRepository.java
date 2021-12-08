package repositories;

import models.LibraryCollection;
import models.MediaBorrowing;
import models.Section;
import models.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MediaBorrowingRepository {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("library-management");
    private static final Logger logger = Logger.getGlobal();

    static {
        logger.setLevel(Level.INFO);
    }

    public static void main(String[] args) {
        try {
            MediaBorrowing mediaBorrowing = creatingMediaBorrowing();
            MediaBorrowing find = findById(1L);
            System.out.println("ID Persist: " + mediaBorrowing.getId());
            System.out.println("ID Find: " + find.getId());
        } finally {
            emf.close();
        }
    }

    private static MediaBorrowing findById(Long id) {
        EntityManager em = null;
        MediaBorrowing mediaBorrowing = null;
        try {
            em = emf.createEntityManager();
            System.out.println("Getting User from database...");
            mediaBorrowing = em.find(MediaBorrowing.class, id);
            System.out.println(mediaBorrowing.toString());
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return mediaBorrowing;
    }

    public static MediaBorrowing insertMediaBorrowing(MediaBorrowing mediaBorrowing){
        EntityManager em = null;
        EntityTransaction et = null;
        try {
            em = emf.createEntityManager();
            et = em.getTransaction();
            et.begin();
            mediaBorrowing = em.merge(mediaBorrowing);
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

        return mediaBorrowing;
    }

    public static MediaBorrowing creatingMediaBorrowing(){
        MediaBorrowing mediaBorrowing = new MediaBorrowing();
        mediaBorrowing.setBorrowed(true);
        mediaBorrowing.setMedias(SectionRepository.creatingMedias());
        mediaBorrowing.setUser(UserRepository.creatingUser());
        mediaBorrowing.setCreatedAt(new Date());
        mediaBorrowing.setUpdatedAt(new Date());

        mediaBorrowing = insertMediaBorrowing(mediaBorrowing);

        return mediaBorrowing;
    }
}
