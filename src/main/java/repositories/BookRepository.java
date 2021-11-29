package repositories;

import models.Address;
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
            Book find = findById(2L);
            System.out.println("ID Persist: " + book.getId());
            System.out.println("ID Find: " + find.getId());
        } finally {
            emf.close();
        }
    }

    private static Book findById(Long id) {
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

    public static Book creatingBook(){
        Book book = new Book();
        book.setTitle("A biblioteca da meia noite");
        book.setDescription("Best seller do new york times");
        book.setGenre("Drama");
        book.setAuthor("Matt Haig");
        book.setEdition("1º");
        book.setPublishingCompany("Bertrand Brasil");
        book.setTotalPages(308);

        book = insertBook(book);

        return book;
    }
}
