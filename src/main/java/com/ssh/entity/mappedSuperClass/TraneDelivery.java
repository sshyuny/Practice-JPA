package com.ssh.entity.mappedSuperClass;

import jakarta.persistence.Entity;

@Entity
public class TraneDelivery extends DeliverySuperClass {
    
    // departureDate 상속
    // arrivalDate 상속

    private String trainTypes;
}
