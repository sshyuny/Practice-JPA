package com.ssh.entity.compositeKey;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import lombok.Data;

/*
 * 부모테이블의 복합키를, 비식별 관계로 갖고 있는 자식 테이블과, 매핑하는 엔티티
 */
@Entity
@Data
public class NoteSet {
    
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "PEN_ID1", referencedColumnName = "PEN_ID1"),
        @JoinColumn(name = "PEN_ID2", referencedColumnName = "PEN_ID2")
    })
    private Pen pen;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "PAPER_ID1", referencedColumnName = "PAPER_ID1"),
        @JoinColumn(name = "PAPER_ID2", referencedColumnName = "PAPER_ID2")
    })
    private Paper paper;

}
