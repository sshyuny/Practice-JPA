package com.ssh.entity.relationMapping;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class RoomMember {
    
    @Id @GeneratedValue
    @Column(name = "ROOM_MEMBER_ID")
    private Long id;

    @OneToOne(mappedBy = "roomMember")
    private Room room;

    private String memberName;
    
}
