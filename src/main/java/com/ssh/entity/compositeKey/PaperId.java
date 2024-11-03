package com.ssh.entity.compositeKey;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

/*
 * @EmbeddedId를 사용할 때의 식별자 클래스
 * 
 * (1) @Embeddable 어노테이션을 붙여야 한다.
 * (2) Serializable 인터페이스를 구현해야 한다.
 * (3) equals, hashCode를 구현해야 한다.
 * (4) 기본 생성자가 있어야 한다.
 * (5) 식별자 클래스는 public 이어야 한다.
 */
@Embeddable
@Data  // equals, hashCode 구현 필요
public class PaperId implements Serializable {
    
    @Column(name = "PAPER_ID1")
    private String id1;

    @Column(name = "PAPER_ID2")
    private String id2;

    public PaperId() {}

}
