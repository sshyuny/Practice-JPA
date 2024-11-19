package com.ssh.entity;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JoinTableTest {

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
    void 조인테이블_테스트() {
        // Hibernate: 
        //     create table ONE_TO_ONE_TABLE_MAPPING (
        //         ONETOONE_PARENT_ID bigint not null,
        //         ONETOTONE_CHILD_ID bigint unique,
        //         primary key (ONETOONE_PARENT_ID)
        //     )
        // Hibernate: 
        //     create table OneToOneChild (
        //         ONETOTONE_CHILD_ID bigint not null,
        //         oneToOneParent_ONETOONE_PARENT_ID bigint unique,
        //         primary key (ONETOTONE_CHILD_ID)
        //     )
        // Hibernate: 
        //     create table OneToOneParent (
        //         ONETOONE_PARENT_ID bigint not null,
        //         name varchar(255),
        //         primary key (ONETOONE_PARENT_ID)
        //     )
    }
}
