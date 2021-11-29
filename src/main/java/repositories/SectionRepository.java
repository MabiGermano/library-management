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
            Section section = creatingSection();
            System.out.println("ID da section: " + section.getId());
        } finally {
            emf.close();
        }
    }

    public static Section insertSection(Section section){
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

        return section;
    }

    public static Section creatingSection(){
        Section section = new Section();
        section.setTitle("Algum titulo");
        section.setMedias(creatingMedias());

        section = insertSection(section);
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
        medias.add(DVDRepository.creatingDVD());
        medias.add(BookRepository.creatingBook());
        medias.add(NewpaperRepository.creatingNewspaper());

        return medias;
    }

}
