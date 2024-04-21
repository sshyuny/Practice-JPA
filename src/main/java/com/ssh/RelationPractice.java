package com.ssh;

import com.ssh.entity.Food;
import com.ssh.entity.FoodType;
import com.ssh.entity.Member;

import jakarta.persistence.EntityManager;

public class RelationPractice {

    static void manyToOneWithTwoWay1(EntityManager em) {
        Food food1 = new Food("김치볶음밥", FoodType.RICE, 4);
        Food food2 = new Food("칼국수", FoodType.NOODLE, 3);
        em.persist(food1);
        em.persist(food2);

        Member member1 = new Member();
        member1.setName("sshyuny");
        member1.getFoods().add(food1);
        member1.getFoods().add(food2);
        em.persist(member1);

        em.flush();
        em.clear();
    }

    static void manyToOneWithTwoWay2(EntityManager em) {
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
