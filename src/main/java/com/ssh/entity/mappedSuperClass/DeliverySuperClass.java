package com.ssh.entity.mappedSuperClass;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

/*
 * 테이블과 매핑되지 않고, 자식 클래스에게 매핑 정보만 제공해준다.
 */
@MappedSuperclass
public abstract class DeliverySuperClass {
    
    @Id  @GeneratedValue
    private Long id;

    private String departureDate;
    private String arrivalDate;

}
