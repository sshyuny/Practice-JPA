package com.ssh.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.TableGenerator;
import lombok.Getter;
import lombok.Setter;

@Entity
@TableGenerator(
    name = "FRUIT_SEQ_GENERATOR",
    table = "MY_SEQUENCES",
    pkColumnValue = "FRUIT_SEQ", allocationSize = 2
)
@Getter @Setter
public class Fruit {
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,
        generator = "FRUIT_SEQ_GENERATOR")
    private Long id;

}
