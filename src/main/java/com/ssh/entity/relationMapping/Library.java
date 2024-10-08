package com.ssh.entity.relationMapping;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Library {
    
    @Id @GeneratedValue
    @Column(name = "LIBRARY_ID")
    private Long id;

    private String name;

    // 일대다 단방향
    @OneToMany
    @JoinColumn(name = "LIBRARY_ID")  // 일대다 단방향에는 @JoinColumn 필요!
    private List<Book> books = new ArrayList<>();

    // 일대다 양방향(사용 비권장!)
    // 연관관계 주인
    @OneToMany
    @JoinColumn(name = "LIBRARY_ID")
    private List<LibraryMember> libraryMembers = new ArrayList<>();

}
