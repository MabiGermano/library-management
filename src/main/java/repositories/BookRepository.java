package repositories;

import models.Address;
import models.Author;
import models.Book;
import models.Newspaper;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookRepository {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("library-management");
    private static final Logger logger = Logger.getGlobal();

    static {
        logger.setLevel(Level.INFO);
    }

    public static Book findById(Long id) {
        EntityManager em = null;
        Book book = null;
        try {
            em = emf.createEntityManager();
            System.out.println("Getting Book from database...");
            book = em.find(Book.class, id);
            System.out.println(book.toString());
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return book;
    }

    public static Book insertBook(Book book){
        EntityManager em = null;
        EntityTransaction et = null;
        try {
            em = emf.createEntityManager();
            et = em.getTransaction();
            et.begin();
            book = em.merge(book);
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

        return book;
    }
}
