package com.ssh.entity.jpql;

import com.ssh.entity.relationMapping.Library;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "Food")
@Table(name = "FOOD_JPQL")
@Getter @Setter
public class Food {
    
    @Id @GeneratedValue
    @Column(name = "food_id")
    private Long id;

    @Column(nullable = false, length = 64)
    private String name;

    @Enumerated(EnumType.STRING)
    private FoodType foodType;

    // 1 ~ 5
    private int preferenceLevel;

    @Lob
    private String detail;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Library member;

    public Food(String name, FoodType foodType, int preferenceLevel) {
        this.name = name;
        this.foodType = foodType;
        this.preferenceLevel = preferenceLevel;
    }

    protected Food() {
    }

}
