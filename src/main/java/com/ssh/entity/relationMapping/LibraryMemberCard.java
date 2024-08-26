package com.ssh.entity.relationMapping;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class LibraryMemberCard {
    
    @Id @GeneratedValue
    @Column(name = "LIBRARY_MEMBER_CARD_ID")
    private Long id;

    private Long cardNum;

    @OneToOne
    @JoinColumn(name = "LIBRARY_MEMBER_ID")
    private LibraryMember libraryMember;
    
}
