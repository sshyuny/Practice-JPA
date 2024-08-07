package com.ssh.entity.relationMapping;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter @Setter
public class Book {
    
    @Id @GeneratedValue
    private Long id;

    private String isbn;
    
}
