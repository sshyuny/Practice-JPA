package com.ssh.entity.inheritance;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "DTYPE_IN_EACH_TABLE")
@Getter @Setter
public abstract class JoinedStrategySalad {
    
    @Id @GeneratedValue
    @Column(name = "EACH_TABLE_SALAD_ID")
    private Long id;

    private String sauce;
    private Integer calorie;

}
