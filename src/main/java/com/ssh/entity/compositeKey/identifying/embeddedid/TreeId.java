package com.ssh.entity.compositeKey.identifying.embeddedid;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Embeddable
public class TreeId implements Serializable {

    @Column(name = "TREE_ID")
    private String treeId;
    
    private Long plantId; // @MapsId("plantId")로 매핑

}
