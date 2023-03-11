package com.laphup.persistence.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@Entity
public class LaptopCategory implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID laptopCategoryUuid;
    @OneToMany(mappedBy = "laptopCategory")
    private Set<Laptop> laptop;
    private String categoryName;
}
