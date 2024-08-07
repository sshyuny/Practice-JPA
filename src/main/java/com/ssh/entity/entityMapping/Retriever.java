package com.ssh.entity.entityMapping;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.TableGenerator;
import lombok.Getter;
import lombok.Setter;

@Entity
@TableGenerator(
    name = "RETRIEVER_SEQ_GENERATOR",
    table = "MY_SEQUENCES",
    pkColumnValue = "RETRIEVER_SEQ", allocationSize = 2
)
@Getter @Setter
public class Retriever {
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,
        generator = "RETRIEVER_SEQ_GENERATOR")
    private Long id;

}
