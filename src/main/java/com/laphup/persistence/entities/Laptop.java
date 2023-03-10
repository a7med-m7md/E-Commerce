package com.laphup.persistence.entities;

import com.laphup.util.enums.Rate;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@Entity
public class Laptop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID uuid;
    @OneToMany(mappedBy = "uuid")
    private Set<LaptopImage> laptopImage;
    @ManyToOne
    @JoinColumn(name = "uuid")
    private LaptopCategory laptopCategory;
    @OneToMany(mappedBy = "laptop")
    private Set<Order> order;
    private String name;
    private double price;
    private Rate rate;
    private int purchasingCount;
}
