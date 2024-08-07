package com.ssh.entity.entityMapping;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Husky {
    
    @Id  // 기본키매핑 - 직접할당전략
    private String id;

    private String name;

}
