package com.ssh.entity;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class MappedSuperClassTest {
    
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
    void 테스트() {
        // Hibernate: 
        // create table AirplaneDelivery (
        //     id bigint not null,
        //     AIRPLAIN_ARRIVAL_DATETIME varchar(255),    부모로부터 받은 컬럼을 재정의한 컬럼!
        //     AIRPLAIN_DEPARTURE_DATETIME varchar(255),    부모로부터 받은 컬럼을 재정의한 컬럼!
        //     airplaneCompany varchar(255),
        //     primary key (id)
        // )
        // Hibernate: 
        // create table TraneDelivery (
        //     id bigint not null,
        //     arrivalDate varchar(255),
        //     departureDate varchar(255),
        //     trainTypes varchar(255),
        //     primary key (id)
        // )
    }

}
