package repositories;

import models.Book;
import models.Newspaper;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookRepository {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("library-management");
    private static final Logger logger = Logger.getGlobal();

    static {
        logger.setLevel(Level.INFO);
    }

    public static void main(String[] args) {
        try {
            Book book = creatingBook();
            System.out.println("ID Book: " + book.getId());
        } finally {
            emf.close();
        }
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

    public static Book creatingBook(){
        Book book = new Book();
        book.setTitle("");
        book.setDescription("");
        book.setGenre("");
        book.setAuthor("");
        book.setEdition("");
        book.setPublishingCompany("");
        book.setTotalPages(0);

        book = insertBook(book);

        return book;
    }
}
