package com.ssh;

import com.ssh.entity.Food;
import com.ssh.entity.FoodType;

import jakarta.persistence.EntityManager;

public class JpqlPractice {
    
    /*
     * JPQL 기본 select 집합
     */
    static void jpql1(EntityManager em) {
        Food food1 = new Food("김치볶음밥", FoodType.RICE, 4);
        Food food2 = new Food("칼국수", FoodType.NOODLE, 3);
        Food food3 = new Food("삼겹살", FoodType.PROTIEN, 3);
        em.persist(food1);
        em.persist(food2);
        em.persist(food3);
        
        em.flush();
        em.clear();

        Long countResult = em.createQuery("select count(f) from Food f", Long.class)
                .getSingleResult();
        Long sumResult = em.createQuery("select sum(f.preferenceLevel) from Food f", Long.class)
                .getSingleResult();

        System.out.println("countResult = " + countResult);
        System.out.println("sumResult = " + sumResult);
    }
    
}
