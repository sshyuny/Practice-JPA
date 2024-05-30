package com.ssh;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.hibernate.id.IdentifierGenerationException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.ssh.entity.Book;
import com.ssh.entity.Dog;
import com.ssh.entity.Food;
import com.ssh.entity.FoodType;
import com.ssh.entity.Fruit;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceException;

public class EntityTest {

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
    void 기본테스트() {
        Food food1 = new Food("김치볶음밥", FoodType.RICE, 4);
        em.persist(food1);
    }

    @Test
    void 기본키매핑_직접할당전략() {
        Dog jindo = new Dog();
        jindo.setDogId("jindo");
        jindo.setName("진돗개");
        em.persist(jindo);

        Dog husky = new Dog();
        husky.setName("시베리안허스키");
        
        // 직접할당전략에서 id 할당 안할시 예외 발생
        assertThrows(IdentifierGenerationException.class , () -> {
            em.persist(husky);
        });
    }

    @Test
    void 기본키매핑_시퀀스전략() {
        for (int i = 0; i < 20; i++) {
            Book book = new Book();
            em.persist(book);
            System.out.println(i + " 번째 책 저장");
        }
        // 시퀀스 값 조회 후 기본키를 매핑함을 확인할수있다!
        
        // JPA 출력 로그
        // Hibernate: 
        //    select
        //         next value for BOOK_SEQ
        // 0 번째 책 저장
        // Hibernate: 
        //     select
        //         next value for BOOK_SEQ
        // 1 번째 책 저장
        // 2 번째 책 저장
        // 3 번째 책 저장
        // 4 번째 책 저장
        // Hibernate: 
        //     select
        //         next value for BOOK_SEQ
        // 5 번째 책 저장
        // 6 번째 책 저장
        // 7 번째 책 저장
        // 8 번째 책 저장
    }

    @Test
    void 기본키매핑_테이블전략() {
        for (int i = 0; i < 10; i++) {
            Fruit fruit = new Fruit();
            em.persist(fruit);
            System.out.println(i + "번: " + fruit.getId() + " id로 저장!");
        }

        // Hibernate: 
        //     select
        //         tbl.next_val 
        //     from
        //         MY_SEQUENCES tbl 
        //     where
        //         tbl.sequence_name=? for update
        // Hibernate: 
        //     update
        //         MY_SEQUENCES 
        //     set
        //         next_val=?  
        //     where
        //         next_val=? 
        //         and sequence_name=?
        // 0번: 1 id로 저장!
        // Hibernate: 
        // select
        //         tbl.next_val 
        //     from
        //         MY_SEQUENCES tbl 
        //     where
        //         tbl.sequence_name=? for update
        // Hibernate: 
        //     update
        //         MY_SEQUENCES 
        //     set
        //         next_val=?  
        //     where
        //         next_val=? 
        //         and sequence_name=?
        // 1번: 2 id로 저장!
        // 2번: 3 id로 저장!
        // Hibernate: 
        //     select
        //         tbl.next_val 
        //     from
        //         MY_SEQUENCES tbl 
        //     where
        //         tbl.sequence_name=? for update
        // Hibernate: 
        //     update
        //         MY_SEQUENCES 
        //     set
        //         next_val=?  
        //     where
        //         next_val=? 
        //         and sequence_name=?
        // 3번: 4 id로 저장!
        // 4번: 5 id로 저장!
    }

}
