import org.junit.AfterClass;
import org.junit.BeforeClass;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class TestInitiator {

    protected static EntityManagerFactory emf;

    @BeforeClass
    public static void setUpClass() {
        emf = Persistence.createEntityManagerFactory("library-management");

        DbUnitUtil.insertDefaultData();
    }

    @AfterClass
    public static void tearDownClass() {
        emf.close();
    }

}
