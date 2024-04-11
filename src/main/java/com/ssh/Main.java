package com.ssh;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-practice");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Food food = new Food();
            food.setName("순대국밥");
            em.persist(food);
            
            em.flush();
            em.clear();
            
            Food selectedFood = em.find(Food.class, food.getId());
            System.out.println("selectedFood.name = " + selectedFood.getName());
            
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
