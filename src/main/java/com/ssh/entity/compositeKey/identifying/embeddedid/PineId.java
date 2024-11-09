package com.ssh.entity.compositeKey.identifying.embeddedid;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
@Embeddable
public class PineId implements Serializable {
    
    @Column(name = "PINE_ID")
    private String pineId;

    private TreeId treeId; // @MapsId("treeId")로 매핑

}
