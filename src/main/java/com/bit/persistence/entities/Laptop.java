package com.bit.persistence.entities;

import com.bit.util.enums.Rate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Entity
public class Laptop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID uuid;
    private String imagPath;
    private String laptopCategory; // convert to map laptopcategory
    private String name;
    private double price;
    private Rate rate;
    private int purchasingCount;
}
