package com.ssh;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Food")
@Getter @Setter
public class Food {
    
    @Id @GeneratedValue
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private FoodType foodType;

    public Food(String name, FoodType foodType) {
        this.name = name;
        this.foodType = foodType;
    }

}
