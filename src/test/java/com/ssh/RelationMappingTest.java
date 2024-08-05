package com.ssh;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.ssh.entity.Food;
import com.ssh.entity.FoodType;
import com.ssh.entity.Member;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

/*
 * 연관관계 매핑 테스트
 */
public class RelationMappingTest {
    
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
    void 다대일() {
        Member member1 = new Member();
        member1.setName("sshyuny");
        em.persist(member1);

        Food food1 = new Food("김치볶음밥", FoodType.RICE, 4);
        Food food2 = new Food("칼국수", FoodType.NOODLE, 3);
        food1.setMember(member1);
        food2.setMember(member1);
        em.persist(food1);
        em.persist(food2);

        em.flush();
        em.clear();

        Food selectedFood1 = em.find(Food.class, food1.getId());
        Food selectedFood2 = em.find(Food.class, food2.getId());
        
        System.out.println("selectedFood1.member = " + selectedFood1.getMember().getName());
        System.out.println("selectedFood2.member = " + selectedFood2.getMember().getName());
    }
}
