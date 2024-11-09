package com.ssh.entity.compositeKey.identifying.embeddedid;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Entity(name =  "PINE_E")
@Getter @Setter
public class Pine {
    
    @EmbeddedId
    private PineId pineId;

    @MapsId("treeId")
    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "TREE_ID"),
        @JoinColumn(name = "PLANT_ID")
    })
    private Tree tree;

    private String name;

}
