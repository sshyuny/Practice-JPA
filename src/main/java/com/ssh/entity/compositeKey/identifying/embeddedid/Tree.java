package com.ssh.entity.compositeKey.identifying.embeddedid;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "TREE_E")
@Getter @Setter
public class Tree {
    
    @EmbeddedId
    private TreeId treeId;

    @MapsId("plantId")
    @ManyToOne
    @JoinColumn(name = "PLANT_ID")
    private Plant plant;

    private String name;

}
