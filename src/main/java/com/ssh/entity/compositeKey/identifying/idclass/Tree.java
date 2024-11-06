package com.ssh.entity.compositeKey.identifying.idclass;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Entity
@IdClass(TreeId.class)
@Getter @Setter
public class Tree {

    @Id @Column(name = "TREE_ID")
    private String treeId;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "PLANT_ID")
    private Plant plant;

    private String name;

}
