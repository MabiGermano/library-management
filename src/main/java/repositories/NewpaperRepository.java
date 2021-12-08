package repositories;

import models.DVD;
import models.MediaBorrowing;
import models.Newspaper;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NewpaperRepository {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("library-management");
    private static final Logger logger = Logger.getGlobal();

    static {
        logger.setLevel(Level.INFO);
    }

    public static void main(String[] args) {
        try {
            Newspaper newspaper = creatingNewspaper();
            Newspaper find = findById(3L);
            System.out.println("ID Persist: " + newspaper.getId());
            System.out.println("ID Find: " + find.getId());
        } finally {
            emf.close();
        }
    }

    private static Newspaper findById(Long id) {
        EntityManager em = null;
        Newspaper newspaper = null;
        try {
            em = emf.createEntityManager();
            System.out.println("Getting User from database...");
            newspaper = em.find(Newspaper.class, id);
            System.out.println(newspaper.toString());
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return newspaper;
    }

    public static Newspaper insertNewspaper(Newspaper newspaper){
        EntityManager em = null;
        EntityTransaction et = null;
        try {
            em = emf.createEntityManager();
            et = em.getTransaction();
            et.begin();
            newspaper = em.merge(newspaper);
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

        return newspaper;
    }

    public static Newspaper creatingNewspaper(){
        Newspaper newspaper = new Newspaper();
        newspaper.setTitle("Recife promove 1° encontro entre capitais para discutir sobre a realização do Carnaval");
        newspaper.setDescription("");
        newspaper.setGenre("Carnaval 2022");
        newspaper.setOriginState("Pernambuco");
        newspaper.setPublishingCompany("Diário de pernambuco");
        newspaper.setReleaseDate(new Date(11-29-2021));

        newspaper = insertNewspaper(newspaper);
        return newspaper;
    }
}
