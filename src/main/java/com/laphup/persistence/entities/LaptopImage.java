package com.laphup.persistence.entities;

import com.laphup.util.enums.ImgaeType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Entity
public class LaptopImage {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID laptopImageUuid;
    @ManyToOne
    @JoinColumn(name = "uuidLaptop")
    private Laptop laptop;
    private String imagPath;
    private ImgaeType imgaeType;
}
