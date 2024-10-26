package com.ssh.entity.mappedSuperClass;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
@AttributeOverrides({
    @AttributeOverride(name = "departureDate", column = @Column(name = "AIRPLAIN_DEPARTURE_DATETIME")),
    @AttributeOverride(name = "arrivalDate", column = @Column(name = "AIRPLAIN_ARRIVAL_DATETIME"))
})
public class AirplaneDelivery extends DeliverySuperClass {
    
    // departureDate 상속
    // arrivalDate 상속

    private String airplaneCompany;

}
