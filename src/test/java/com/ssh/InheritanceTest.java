package com.ssh;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.ssh.entity.inheritance.JoinedStrategySalmonSalad;
import com.ssh.entity.inheritance.JoinedStrategyTomatoSalad;
import com.ssh.entity.inheritance.PerClassStrategyTomatoSalad;
import com.ssh.entity.inheritance.SingleTableStrategyTomatoSalad;

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

        JoinedStrategySalmonSalad salmonSalad = new JoinedStrategySalmonSalad();
        salmonSalad.setCalorie(400);
        salmonSalad.setSauce("머스타드");
        salmonSalad.setSalmonOrigin("노르웨이");
        em.persist(salmonSalad);

        JoinedStrategyTomatoSalad tomatoSalad = new JoinedStrategyTomatoSalad();
        tomatoSalad.setCalorie(300);
        tomatoSalad.setSauce("발사믹");
        tomatoSalad.setTomatoType("대추방울토마토");
        em.persist(tomatoSalad);

        em.flush();
        em.clear();

        JoinedStrategyTomatoSalad findedOne = em.find(JoinedStrategyTomatoSalad.class, tomatoSalad.getId());
        assertTrue(findedOne.getSauce().equals("발사믹"));

        // 확인할 부분
        // [create table] 슈퍼 타입 EachTableSalad 테이블과 서브타입 EachTableSalmonSalad, EachTableTomatoSalad 테이블 각각 생성한다.
        // [insert 어떻게] EachTableSalad와 EachTableSalmonSalad 따로따로 insert 한다
        // [select 어떻게] join으로 가져온다

        // Hibernate: 
        //     create table JoinedStrategySalad (
        //         calorie integer,    (공통 필드!)
        //         EACH_TABLE_SALAD_ID bigint not null,
        //         DTYPE_IN_EACH_TABLE varchar(31) not null,
        //         sauce varchar(255),    (공통 필드!)
        //         primary key (EACH_TABLE_SALAD_ID)
        //     )
        // Hibernate: 
        //     create table JoinedStrategySalmonSalad (
        //         EACH_TABLE_SALAD_ID bigint not null,
        //         salmonOrigin varchar(255),
        //         primary key (EACH_TABLE_SALAD_ID)
        //     )
        // Hibernate: 
        //     create table JoinedStrategyTomatoSalad (
        //         EACH_TABLE_SALAD_ID bigint not null,
        //         tomatoType varchar(255),
        //         primary key (EACH_TABLE_SALAD_ID)
        //     )
    }

    @Test
    void 단일테이블전략() {

        SingleTableStrategyTomatoSalad tomatoSalad = new SingleTableStrategyTomatoSalad();
        tomatoSalad.setCalorie(300);
        tomatoSalad.setSauce("발사믹");
        tomatoSalad.setTomatoType("대추방울토마토");
        em.persist(tomatoSalad);

        em.flush();
        em.clear();

        SingleTableStrategyTomatoSalad findedOne = em.find(SingleTableStrategyTomatoSalad.class, tomatoSalad.getId());
        assertTrue(findedOne.getSauce().equals("발사믹"));

        // 확인할 부분
        // [create table] OneTableSalad 테이블 하나를 생성하며 이는 자식 테이블의 필드 전부를 포함하고 있다.
        // [insert] OneTableSalad 테이블 하나에 insert 한다.
        // [select] OneTableSalad 테이블 하나에서 select 한다.

        // Hibernate: 
        //     create table SingleTableStrategySalad (
        //         calorie integer,
        //         ONE_TABLE_SALAD_ID bigint not null,
        //         DTYPE_IN_ONE_TABLE varchar(31) not null,
        //         salmonOrigin varchar(255),
        //         sauce varchar(255),
        //         tomatoType varchar(255),
        //         primary key (ONE_TABLE_SALAD_ID)
        //     )
    }

    @Test
    void 구현_클래스마다_테이븰_전략() {
        PerClassStrategyTomatoSalad tomatoSalad = new PerClassStrategyTomatoSalad();
        tomatoSalad.setCalorie(300);
        tomatoSalad.setSauce("발사믹");
        tomatoSalad.setTomatoType("대추방울토마토");
        em.persist(tomatoSalad);

        em.flush();
        em.clear();

        PerClassStrategyTomatoSalad findedOne = em.find(PerClassStrategyTomatoSalad.class, tomatoSalad.getId());
        assertTrue(findedOne.getSauce().equals("발사믹"));

        // Hibernate: 
        //     create table PerClassStrategySalmonSalad (
        //         calorie integer,
        //         TABLE_SALAD_ID bigint not null,
        //         salmonOrigin varchar(255),
        //         sauce varchar(255),
        //         primary key (TABLE_SALAD_ID)
        //     )
        // Hibernate: 
        //     create table PerClassStrategyTomatoSalad (
        //         calorie integer,
        //         TABLE_SALAD_ID bigint not null,
        //         sauce varchar(255),
        //         tomatoType varchar(255),
        //         primary key (TABLE_SALAD_ID)
        //     )
    }
}
