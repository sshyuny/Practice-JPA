package com.ssh.entity.tableMapping;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "BLUE" , uniqueConstraints = {@UniqueConstraint (
    name = "TRANSPARENT_TYPE_UNIQUE",
    columnNames = {"transparent", "blueType"}
)})
@Getter @Setter
public class Blue {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BLUE_ID")
    private Long id;

    @Column
    private int transparent;

    @Enumerated(EnumType.STRING)
    private BlueType blueType;

    public Blue() {
    }

}
