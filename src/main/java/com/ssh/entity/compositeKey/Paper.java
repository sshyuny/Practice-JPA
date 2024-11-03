package com.ssh.entity.compositeKey;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @ToString
public class Paper {
    
    @EmbeddedId
    private PaperId id;

    private String name;

}
