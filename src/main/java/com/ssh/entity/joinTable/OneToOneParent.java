package com.ssh.entity.joinTable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class OneToOneParent {
    
    @Id @GeneratedValue
    @Column(name = "ONETOONE_PARENT_ID")
    private Long id;

    private String name;

    @OneToOne
    @JoinTable(name = "ONE_TO_ONE_TABLE_MAPPING",
        joinColumns = @JoinColumn(name = "ONETOONE_PARENT_ID"), 
        inverseJoinColumns = @JoinColumn(name = "ONETOTONE_CHILD_ID"))
    private OneToOneChild child;

}
