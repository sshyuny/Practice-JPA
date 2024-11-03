package com.ssh.entity;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.ssh.entity.compositeKey.NoteSet;
import com.ssh.entity.compositeKey.Paper;
import com.ssh.entity.compositeKey.PaperId;
import com.ssh.entity.compositeKey.Pen;
import com.ssh.entity.compositeKey.PenId;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

/*
 * 복합키와 식별관계 매핑
 */
public class CompositeKeyTest {
    
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
    void 어노테이션_IdClass_사용한_복합키_매핑() {

        Pen pen = new Pen();
        pen.setId1("id1");
        pen.setId2("id2");
        pen.setName("스탠다드펜");
        em.persist(pen);

        // 식별자 클래스 IdClassStandardPenId를 사용하진 않았지만
        // 내부적으로 생성하고 영속성 컨텍스트의 키로 사용한다.

        em.flush();
        em.clear();

        PenId penId = new PenId();
        penId.setId1("id1");
        penId.setId2("id2");
        Pen findedPen = em.find(Pen.class, penId);

        System.out.println(findedPen);
    }

    @Test
    void 어노테이션_EmbeddedId_사용한_복합키_매핑() {

        PaperId paperId = new PaperId();
        Paper paper = new Paper();
        paperId.setId1("id1");
        paperId.setId2("id2");
        paper.setId(paperId);
        paper.setName("A4용지");
        em.persist(paper);

        em.flush();
        em.clear();

        Paper findedPaper = em.find(Paper.class, paperId);
        System.out.println(findedPaper);
    }

    @Test
    void 비식별관계_외래키매핑() {

        Pen pen = new Pen();
        pen.setId1("id1");
        pen.setId2("id2");
        pen.setName("standard pen");
        em.persist(pen);

        PaperId paperId = new PaperId();
        Paper paper = new Paper();
        paperId.setId1("id1");
        paperId.setId2("id2");
        paper.setId(paperId);
        paper.setName("A4용지");
        em.persist(paper);

        NoteSet set =  new NoteSet();
        set.setPen(pen);
        set.setPaper(paper);
        em.persist(set);

        em.flush();
        em.clear();

        NoteSet findedSet = em.find(NoteSet.class, set.getId());
        System.out.println(findedSet);
    }
}
