package com.ssh.entity.inheritance;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("Salmon")
@Getter @Setter
public class EachTableSalmonSalad extends EachTableSalad {
    
    private String salmonOrigin;

}
