package com.ssh.entity.compositeKey;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@IdClass(PenId.class)
@Getter @Setter @ToString
public class Pen {
    
    @Id
    @Column(name = "PEN_ID1")
    private String id1;  // IdClassStandardPenId.id1과 연결

    @Id
    @Column(name = "PEN_ID2")
    private String id2;  // IdClassStandardPenId.id2과 연결

    private String name;
    
}
