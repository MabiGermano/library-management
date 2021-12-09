import models.User;
import org.junit.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import static org.junit.Assert.assertEquals;

public class UsuarioTeste {
    private static EntityManagerFactory emf;
    private EntityManager em;
    private EntityTransaction et;

    @BeforeClass
    public static void setUpClass() {
        emf = Persistence.createEntityManagerFactory("library_management");
//        DbUnitUtil.inserirDados();
    }

    @AfterClass
    public static void tearDownClass() {
        emf.close();
    }

    @Before
    public void setUp() {
        em = emf.createEntityManager();
        et = em.getTransaction();
        et.begin();
    }

    @After
    public void tearDown() {
        if (!et.getRollbackOnly()) {
            et.commit();
        }
        em.close();
    }

    @Test
    public void consultarUsuario() {
        User usuario = em.find(User.class, 1L);
        assertEquals("908.507.040-65", usuario.getCpf());
    }
}
