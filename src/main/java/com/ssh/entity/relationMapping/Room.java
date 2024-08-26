package com.ssh.entity.relationMapping;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Room {

    @Id @GeneratedValue
    private Long id;

    private String roomNumber;

    // 일대일 단방향
    // 주테이블(연관관계 주인 엔티티의 테이블)에 외래키
    @OneToOne
    @JoinColumn(name = "ROOM_KEY_ID")
    private RoomKey roomKey;

    // 일대일 양방향
    // 주테이블(연관관계 주인 엔티티의 테이블)에 외래키
    @OneToOne
    @JoinColumn(name = "ROOM_MEMBER_ID")
    private RoomMember roomMember;

    
}


