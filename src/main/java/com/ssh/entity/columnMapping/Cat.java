package com.ssh.entity.columnMapping;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter @Setter
public class Cat {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CAT_ID")
    private Long id;

    @Column(nullable = false, length = 64)
    private String name;

    @Enumerated(EnumType.STRING)
    private CatBreed catBreed;

    private int size;

    @Lob
    private String detail;

    public Cat(String name, CatBreed catBreed, int size) {
        this.name = name;
        this.catBreed = catBreed;
        this.size = size;
    }

    protected Cat() {
    }

}
