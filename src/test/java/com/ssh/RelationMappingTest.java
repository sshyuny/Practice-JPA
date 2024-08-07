package com.ssh;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.ssh.entity.relationMapping.Book;
import com.ssh.entity.relationMapping.Library;

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
    void 일대다_단방향() {
        Library library = new Library();
        em.persist(library);

        Book bookA = new Book();
        bookA.setIsbn("987-654");
        em.persist(bookA);
        Book bookB = new Book();
        bookB.setIsbn("987-655");
        em.persist(bookB);
        
        library.getBooks().add(bookA);
        library.getBooks().add(bookB);

        // Library의 필드를 변경했지만
        // 실행하면 Book 테이블에 update 쿼리 나간 것 확인할 수 있다!
    }
}
