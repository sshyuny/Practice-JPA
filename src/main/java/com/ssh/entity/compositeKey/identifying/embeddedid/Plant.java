package com.ssh.entity.compositeKey.identifying.embeddedid;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "PLANT_E")
@Getter @Setter
public class Plant {
    
    @Id @GeneratedValue
    @Column(name = "PLANT_ID")
    private Long plantId;

    private String name;

}
