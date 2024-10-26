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
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE_IN_ONE_TABLE")
@Getter @Setter
public abstract class SingleTableStrategySalad {
    
    @Id @GeneratedValue
    @Column(name = "ONE_TABLE_SALAD_ID")
    private Long id;

    private String sauce;
    private Integer calorie;

}
