package com.ssh;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.ssh.entity.relationMapping.Book;
import com.ssh.entity.relationMapping.Library;
import com.ssh.entity.relationMapping.LibraryMember;
import com.ssh.entity.relationMapping.LibraryMemberCard;
import com.ssh.entity.relationMapping.Room;
import com.ssh.entity.relationMapping.RoomKey;
import com.ssh.entity.relationMapping.RoomMember;

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

        // 또 Book에 FK LIBRARY_ID 컬럼이 들어있다.

        // Hibernate: 
        // create table Book (
        //     BOOK_ID bigint not null,
        //     LIBRARY_ID bigint,
        //     isbn varchar(255),
        //     primary key (BOOK_ID)
        // )
    }

    @Test
    void 일대다_양방향() {
        Library library = new Library();
        library.setName("마포도서관");
        em.persist(library);

        LibraryMember libraryMemberA = new LibraryMember();
        libraryMemberA.setName("A회원");
        em.persist(libraryMemberA);        
        LibraryMember libraryMemberB = new LibraryMember();
        libraryMemberB.setName("B회원");
        em.persist(libraryMemberB);

        library.getLibraryMembers().add(libraryMemberA);
        library.getLibraryMembers().add(libraryMemberB);

        em.flush();
        em.clear();

        Library findedLibrary = em.find(Library.class, library.getId());
        List<LibraryMember> libraryMembers = findedLibrary.getLibraryMembers();
        for (LibraryMember member : libraryMembers) {
            System.out.println("찾은 도서관 회원 이름 = " + member.getName());
        }

        em.flush();
        em.clear();

        LibraryMember findedLibraryMemberA = em.find(LibraryMember.class, libraryMemberA.getId());
        System.out.println("찾은 도서관 이름 = " + findedLibraryMemberA.getLibrary().getName());
    }

    @Test
    void 일대일_주테이블에_외래키_단방향() {
        RoomKey roomKey = new RoomKey();
        roomKey.setKeyNumber(2345L);
        em.persist(roomKey);

        Room room = new Room();
        room.setRoomNumber("101");
        room.setRoomKey(roomKey);
        em.persist(room);

        em.flush();
        em.clear();

        Room findedRoom = em.find(Room.class, room.getId());
        System.out.println("Room에서 찾은 RoomKey의 번호 = " + findedRoom.getRoomKey().getKeyNumber());
    }

    @Test
    void 일대일_주테이블에_외래키_양방향() {
        RoomMember roomMember = new RoomMember();
        roomMember.setMemberName("sshyuny");
        em.persist(roomMember);

        Room room = new Room();
        room.setRoomNumber("101");
        room.setRoomMember(roomMember);
        em.persist(room);

        em.flush();
        em.clear();

        RoomMember findedRoomMember = em.find(RoomMember.class, roomMember.getId());
        System.out.println("RoomMember에서 찾은 Room의 번호 = " + findedRoomMember.getRoom().getRoomNumber());

        Room findedRoom = em.find(Room.class, room.getId());
        System.out.println("Room에서 찾은 RoomMember의 이름 = " + findedRoom.getRoomMember().getMemberName());
    }

    @Test
    void 일대일_대상테이블에_외래키_양방향_NullPointerException() {
        LibraryMemberCard libraryMemberCard = new LibraryMemberCard();
        libraryMemberCard.setCardNum(2345L);
        em.persist(libraryMemberCard);
        
        LibraryMember libraryMember = new LibraryMember();
        libraryMember.setName("sshyuny");
        libraryMember.setLibraryMemberCard(libraryMemberCard);  // 연관관계 주인이 아닌 곳에서 넣어버림!
        em.persist(libraryMember);

        em.flush();
        em.clear();

        LibraryMember findedLibraryMember =  em.find(LibraryMember.class, libraryMember.getId());
        assertThrows(NullPointerException.class , () -> {
            findedLibraryMember.getLibraryMemberCard().getCardNum();
        });
    }

    @Test
    void 일대일_대상테이블에_외래키_양방향() {
        LibraryMember libraryMember = new LibraryMember();
        libraryMember.setName("sshyuny");
        em.persist(libraryMember);
        
        LibraryMemberCard libraryMemberCard = new LibraryMemberCard();
        libraryMemberCard.setCardNum(2345L);
        libraryMemberCard.setLibraryMember(libraryMember);  // 연관관계 주인에서 넣어준다
        em.persist(libraryMemberCard);
        
        em.flush();
        em.clear();

        LibraryMember findedLibraryMember =  em.find(LibraryMember.class, libraryMember.getId());
        System.out.println("LibraryMember에서 찾은 LibraryMemberCard의 번호 = " + findedLibraryMember.getLibraryMemberCard().getCardNum());

        LibraryMemberCard findedLibraryMemberCard = em.find(LibraryMemberCard.class, libraryMemberCard.getId());
        System.out.println("LibraryMemberCard에서 찾은 LibraryMember의 이름 = " + findedLibraryMemberCard.getLibraryMember().getName());
    }

}
