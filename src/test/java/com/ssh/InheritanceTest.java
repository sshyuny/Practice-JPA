package com.ssh;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.ssh.entity.inheritance.EachTableSalmonSalad;
import com.ssh.entity.inheritance.EachTableTomatoSalad;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class InheritanceTest {

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
    void 조인전략() {
        EachTableSalmonSalad salmonSalad = new EachTableSalmonSalad();
        salmonSalad.setCalorie(400);
        salmonSalad.setSauce("머스타드");
        salmonSalad.setSalmonOrigin("노르웨이");
        em.persist(salmonSalad);

        EachTableTomatoSalad tomatoSalad = new EachTableTomatoSalad();
        tomatoSalad.setCalorie(300);
        tomatoSalad.setSauce("발사믹");
        tomatoSalad.setTomatoType("대추방울토마토");
        em.persist(tomatoSalad);

        em.flush();
        em.clear();

        EachTableTomatoSalad findedEachTableTomatoSalad = em.find(EachTableTomatoSalad.class, tomatoSalad.getId());
        System.out.println("찾은 토마토 샐러드의 소스 = " + findedEachTableTomatoSalad.getTomatoType());
    }
}
