package com.ssh.entity.relationMapping;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class RoomKey {

    @Id @GeneratedValue
    @Column(name = "ROOM_KEY_ID")
    private Long id;

    private Long keyNumber;
    
}
