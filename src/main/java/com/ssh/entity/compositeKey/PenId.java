package com.ssh.entity.compositeKey;

import java.io.Serializable;

import lombok.Data;

/*
 * @IdClass를 사용할 때의 식별자 클래스
 * 
 * (1) 엔티티의 속성명(id1, id2)과 해당 식별자 클래스의 속성명(id1, id2)이 같아야 한다.
 * (2) Serializable 인터페이스를 구현해야 한다.
 * (3) equals, hashCode를 구현해야 한다.
 * (4) 기본 생성자가 있어야 한다.
 * (5) 식별자 클래스는 public 이어야 한다.
 */
@Data
public class PenId implements Serializable {
    
    private String id1;
    private String id2;

    public PenId() {}

}
