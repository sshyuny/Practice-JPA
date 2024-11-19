package com.ssh.entity.joinTable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
// import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class OneToOneChild {
    
    @Id @GeneratedValue
    @Column(name = "ONETOTONE_CHILD_ID")
    private Long id;

    // 양방향
    // @OneToOne
    // private OneToOneParent oneToOneParent;

}
