package com.ssh.entity.relationMapping;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class LibraryMember {
    
    @Id @GeneratedValue
    @Column(name = "LIBRARY_MEMBER_ID")
    private Long id;

    private String name;

    // 일대다 양방향
    // 연관관계 주인 아님!
    @ManyToOne
    @JoinColumn(name = "LIBRARY_ID", insertable = false, updatable = false)
    private Library library;

    // 일대일 양방향
    // 대상테이블에 외래키
    @OneToOne(mappedBy = "libraryMember")
    private LibraryMemberCard libraryMemberCard;

}
