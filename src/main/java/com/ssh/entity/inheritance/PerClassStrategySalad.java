package com.ssh.entity.inheritance;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter @Setter
public abstract class PerClassStrategySalad {
    
    @Id @GeneratedValue
    @Column(name = "TABLE_SALAD_ID")
    private Long id;

    private String sauce;
    private Integer calorie;

}
