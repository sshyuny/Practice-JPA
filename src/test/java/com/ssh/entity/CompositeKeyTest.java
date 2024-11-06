package com.ssh.entity;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.ssh.entity.compositeKey.Paper;
import com.ssh.entity.compositeKey.PaperId;
import com.ssh.entity.compositeKey.Pen;
import com.ssh.entity.compositeKey.PenId;
import com.ssh.entity.compositeKey.identifying.idclass.Pine;
import com.ssh.entity.compositeKey.identifying.idclass.Plant;
import com.ssh.entity.compositeKey.identifying.idclass.Tree;
import com.ssh.entity.compositeKey.identifying.idclass.TreeId;
import com.ssh.entity.compositeKey.nonIdentifying.NoteSet;

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
    void 비식별관계매핑() {

        // 부모클래스 Pen과 Paper 각각 먼저 저장
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

        // NoteSet 테이블은 Paper과 Pen 테이블과 비식별 관계로 FK 매핑한다.
        NoteSet set =  new NoteSet();
        set.setPen(pen);
        set.setPaper(paper);
        em.persist(set);

        em.flush();
        em.clear();

        NoteSet findedSet = em.find(NoteSet.class, set.getId());
        System.out.println(findedSet);
    }

    @Test
    void 식별관계매핑_어노테이션_IdClass사용() {

        // Plant > Tree > Pine
        Plant plant = new Plant();
        plant.setName("식물");
        em.persist(plant);

        Tree tree = new Tree();
        tree.setTreeId("TREE_ID_1");
        tree.setPlant(plant);
        tree.setName("나무");
        em.persist(tree);

        Pine pine = new Pine();
        pine.setPineId("PINE_ID_1");
        pine.setName("소나무");
        pine.setTree(tree);
        em.persist(pine);

        em.flush();
        em.clear();

        Plant findedPlant = em.find(Plant.class, plant.getId());

        TreeId treeId = new TreeId();
        treeId.setTreeId("TREE_ID_1");
        treeId.setPlant(findedPlant);

        Tree findedTree = em.find(Tree.class, treeId);
        System.out.println(findedTree);

        // Hibernate: 
        //     create table Pine (
        //         TREE_ID bigint not null,
        //         PINE_ID varchar(255) not null,
        //         PLANT_ID varchar(255) not null,
        //         name varchar(255),
        //         primary key (TREE_ID, PINE_ID, PLANT_ID)
        //     )
    }
}
