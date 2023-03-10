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
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuidLaptop;
    @OneToMany(mappedBy = "laptop")
    private Set<LaptopImage> laptopImage;
    @ManyToOne
    @JoinColumn(name = "laptopCategoryUuid")
    private LaptopCategory laptopCategory;
    @OneToMany(mappedBy = "laptop")
    private Set<OrderDetails> orderDetails;
    private String name;
    private double price;
    private Rate rate;
    private int purchasingCount;
}
