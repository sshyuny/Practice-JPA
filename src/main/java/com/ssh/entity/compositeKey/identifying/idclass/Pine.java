package com.ssh.entity.compositeKey.identifying.idclass;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@IdClass(PineId.class)
@Getter @Setter
public class Pine {
    
    @Id @Column(name = "PINE_ID")
    String pineId;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "TREE_ID"),
        @JoinColumn(name = "PLANT_ID")
    })
    @Id
    Tree tree;

    private String name;

}
