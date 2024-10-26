package com.ssh.entity.inheritance;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class PerClassStrategyTomatoSalad extends PerClassStrategySalad {
    
    private String tomatoType;

}
