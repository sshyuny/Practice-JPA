package com.ssh.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Dog {
    
    @Id  // 기본키매핑 - 직접할당전략
    private String dogId;

    private String name;

}
