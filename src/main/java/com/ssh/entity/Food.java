package com.ssh.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Food")
@Getter @Setter
public class Food {
    
    @Id @GeneratedValue
    @Column(name = "food_id")
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private FoodType foodType;

    // 1 ~ 5
    private int preferenceLevel;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public Food(String name, FoodType foodType, int preferenceLevel) {
        this.name = name;
        this.foodType = foodType;
        this.preferenceLevel = preferenceLevel;
    }

    protected Food() {
    }

}
