package com.ssh;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Main {

    static void jpql1(EntityManager em) {
        Food food1 = new Food("쌀밥", FoodType.RICE);
        Food food2 = new Food("칼국수", FoodType.NOODLE);
        Food food3 = new Food("삼겹살", FoodType.PROTIEN);
        em.persist(food1);
        em.persist(food2);
        em.persist(food3);
        
        em.flush();
        em.clear();

        Long result = em.createQuery("select count(f) from Food f", Long.class)
                .getSingleResult();        

        System.out.println("result = " + result);
    }

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-practice");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            jpql1(em);
            
            tx.commit();
            
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        emf.close();;
    }
}
