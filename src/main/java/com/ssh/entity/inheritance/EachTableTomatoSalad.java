package com.ssh.entity.inheritance;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("Tomato")
@Getter @Setter
public class EachTableTomatoSalad extends EachTableSalad {
    
    private String tomatoType;

}
