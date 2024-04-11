import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class EntityTest {

    static EntityManagerFactory emf;
    static EntityManager em;
    static EntityTransaction tx;
    
    @BeforeAll
    static void beforeAll() {
        emf = Persistence.createEntityManagerFactory("jpa-practice");
        em = emf.createEntityManager();
        tx = em.getTransaction();

        tx.begin();
    }

    @AfterAll
    static void afterAll() {
        tx.commit();
        em.close();
        emf.close();
    }

    @Test
    void test() {
        Food food = new Food();
        food.setName("순대국밥");
        em.persist(food);
    }
}
