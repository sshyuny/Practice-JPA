package com.ssh.entity.entityMapping;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

@Entity
@SequenceGenerator (
    name = "JINDO_SEQ_GENERATOR",
    sequenceName = "JINDO_SEQ",
    initialValue = 1, allocationSize = 4
)
@Getter @Setter
public class Jindo {
    
    @Id  // 기본키매핑 - 시퀀스전략
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "JINDO_SEQ_GENERATOR")
    private Long bookId;

    private String name;

}
