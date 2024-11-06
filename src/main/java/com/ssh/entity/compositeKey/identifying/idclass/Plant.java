package com.ssh.entity.compositeKey.identifying.idclass;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Plant {
    
    @Id @Column(name = "PLANT_ID")
    @GeneratedValue
    private Long id;

    private String name;
    
}
