package repositories;

import models.Address;
import models.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserRepository {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("library-management");
    private static final Logger logger = Logger.getGlobal();

    static {
        logger.setLevel(Level.INFO);
    }

    public static void main(String[] args) {
        try {
            User user = creatingUser();
            User find = findById(1L);
            System.out.println("ID User: " + user.getId());
        } finally {
            emf.close();
        }
    }

    public static User findById(Long id) {
        EntityManager em = null;
        User user = new User();
        try {
            em = emf.createEntityManager();
            System.out.println("Getting User from database...");
            user = em.find(User.class, id);
            System.out.println(user.toString());
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return user;
    }

    public static User insertUser(User user){
        EntityManager em = null;
        EntityTransaction et = null;
        try {
            em = emf.createEntityManager();
            et = em.getTransaction();
            et.begin();
            user = em.merge(user);
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

        return user;
    }

    public static User creatingUser(){
        User user = new User();
        user.setAddress(AddressRepository.creatingAddress());
        user.setCpf("908.507.040-65");
        user.setEmail("teste@teste.com.br");
        user.setName("Usuário de teste");
        user.setRegistration(UUID.randomUUID().toString());
        user.setTel("(81) 98811-6934");

        user = insertUser(user);
        return user;
    }
}
