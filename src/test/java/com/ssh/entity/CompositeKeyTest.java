package com.ssh.entity;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.ssh.entity.compositeKey.Paper;
import com.ssh.entity.compositeKey.PaperId;
import com.ssh.entity.compositeKey.Pen;
import com.ssh.entity.compositeKey.PenId;
import com.ssh.entity.compositeKey.identifying.embeddedid.PineId;
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

    @Test
    void 식별관계매핑_어노테이션_EmbeddedId사용() {
        // create table PLANT_E (
        //     PLANT_ID bigint not null,
        //     name varchar(255),
        //     primary key (PLANT_ID)
        // )
        // create table TREE_E (
        //     PLANT_ID varchar(255) not null,
        //     TREE_ID varchar(255) not null,
        //     name varchar(255),
        //     primary key (PLANT_ID, TREE_E_ID)
        // )
        // create table PINE_E (
        //     PINE_ID varchar(255) not null,
        //     PLANT_ID varchar(255) not null,
        //     TREE_ID varchar(255) not null,
        //     name varchar(255),
        //     primary key (PINE_ID, PLANT_ID, TREE_ID)
        // )

        // Plant > Tree > Pine
        com.ssh.entity.compositeKey.identifying.embeddedid.Plant plant = new com.ssh.entity.compositeKey.identifying.embeddedid.Plant();
        plant.setName("식물");
        em.persist(plant);

        com.ssh.entity.compositeKey.identifying.embeddedid.Tree tree = new com.ssh.entity.compositeKey.identifying.embeddedid.Tree();
        tree.setTreeId(new com.ssh.entity.compositeKey.identifying.embeddedid.TreeId("id1", plant.getPlantId()));
        tree.setPlant(plant);
        tree.setName("나무");
        em.persist(tree);

        com.ssh.entity.compositeKey.identifying.embeddedid.Pine pine = new com.ssh.entity.compositeKey.identifying.embeddedid.Pine();
        pine.setPineId(new PineId("id1", tree.getTreeId()));
        pine.setName("소나무");
        pine.setTree(tree);
        em.persist(pine);

        em.flush();
        em.clear();

        com.ssh.entity.compositeKey.identifying.embeddedid.Pine findedPine = em.find(com.ssh.entity.compositeKey.identifying.embeddedid.Pine.class, pine.getPineId());
        System.out.println(findedPine);

        // select
        //     p1_0.PINE_ID,
        //     p1_0.TREE_ID,
        //     p1_0.PLANT_ID,
        //     p1_0.name,
        //     t1_0.PLANT_ID,
        //     t1_0.TREE_ID,
        //     t1_0.name,
        //     p2_0.PLANT_ID,
        //     p2_0.name 
        // from
        //     PINE_E p1_0 
        // join
        //     TREE_E t1_0 
        //         on t1_0.PLANT_ID=p1_0.TREE_ID 
        //         and t1_0.TREE_ID=p1_0.PLANT_ID 
        // left join
        //     PLANT_E p2_0 
        //         on p2_0.PLANT_ID=t1_0.PLANT_ID 
        // where
        //     (
        //         p1_0.PINE_ID, p1_0.TREE_ID, p1_0.PLANT_ID
        //     ) in ((?, ?, ?))
    }
}
