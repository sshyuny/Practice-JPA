package com.ssh;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.ssh.entity.jpql.Food;
import com.ssh.entity.jpql.FoodType;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JpqlTest {
    
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

    void createBaseData(EntityManager em) {
        Food food1 = new Food("김치볶음밥", FoodType.RICE, 4);
        Food food2 = new Food("칼국수", FoodType.NOODLE, 3);
        Food food3 = new Food("삼겹살", FoodType.PROTIEN, 2);
        em.persist(food1);
        em.persist(food2);
        em.persist(food3);
    }

    @Test
    void 프로젝션_테스트() {
        createBaseData(em);

        List<Object[]> results = em.createQuery("select f.name, f.preferenceLevel from Food f order by f.preferenceLevel")
                        .getResultList();
        
        Object[] result1 = results.get(0);
        Object[] result2 = results.get(1);
        Object[] result3 = results.get(2);

        assertEquals(result1[0], "삼겹살");
        assertEquals(result2[0], "칼국수");
        assertEquals(result3[0], "김치볶음밥");
    }
}
