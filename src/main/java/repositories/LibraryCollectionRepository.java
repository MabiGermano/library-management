package repositories;

import models.LibraryCollection;
import models.Section;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LibraryCollectionRepository {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("library-management");
    private static final Logger logger = Logger.getGlobal();

    static {
        logger.setLevel(Level.INFO);
    }

    public static void main(String[] args) {
        try {
            LibraryCollection libraryCollection = creatingLibraryCollection();
            System.out.println("ID LibraryCollection: " + libraryCollection.getId());
        } finally {
            emf.close();
        }
    }

    public static LibraryCollection insertLibraryCollection(LibraryCollection libraryCollection){
        EntityManager em = null;
        EntityTransaction et = null;
        try {
            em = emf.createEntityManager();
            et = em.getTransaction();
            et.begin();
            libraryCollection = em.merge(libraryCollection);
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

        return libraryCollection;
    }

    public static LibraryCollection creatingLibraryCollection(){
        LibraryCollection libraryCollection = new LibraryCollection();
        libraryCollection.setSections(SectionRepository.creatingListSection());

        for (Section section : libraryCollection.getSections()){
            section.setLibraryCollection(libraryCollection);
        }

        libraryCollection = insertLibraryCollection(libraryCollection);

        return libraryCollection;
    }
}
