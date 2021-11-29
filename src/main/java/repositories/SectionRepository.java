package repositories;

import models.Book;
import models.DVD;
import models.Media;
import models.Section;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SectionRepository {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("library-management");
    private static final Logger logger = Logger.getGlobal();

    static {
        logger.setLevel(Level.INFO);
    }

    public static void main(String[] args) {
        try {
            Long id = insertSection(creatingSection());
        } finally {
            emf.close();
        }
    }

    public static Long insertSection(Section section){
        EntityManager em = null;
        EntityTransaction et = null;
        try {
            em = emf.createEntityManager();
            et = em.getTransaction();
            et.begin();
            section = em.merge(section);
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

        return section.getId();
    }

    public static Section creatingSection(){
        Section section = new Section();
        section.setTitle("Algum titulo");
        section.setMedias(creatingMedias());
        return section;
    }

    public static List<Section> creatingListSection(){
        List<Section> sections = new ArrayList<>();

        sections.add(creatingSection());
        sections.add(creatingSection());
        sections.add(creatingSection());

        return sections;
    }

    public static HashSet<Media> creatingMedias(){
        HashSet medias = new HashSet();
        medias.add(creatingDVD());

        return medias;
    }

    public static Media creatingDVD(){
        DVD dvd = new DVD();
        dvd.setTitle("Harry Potter e a Pedra Filosofal");
        dvd.setArtist("Steven Kloves");
        dvd.setDuration(152);
        dvd.setYear("2001");
        dvd.setDescription("Harry Potter é um garoto órfão que vive infeliz com seus tios, os Dursleys. Ele recebe uma carta contendo um convite para ingressar em Hogwarts, uma famosa escola especializada em formar jovens bruxos. Inicialmente, Harry é impedido de ler a carta por seu tio, mas logo recebe a visita de Hagrid, o guarda-caça de Hogwarts, que chega para levá-lo até a escola.");
        dvd.setGenre("Fantasia");
        dvd.setStockQuantity(3);

        return dvd;
    }
}
