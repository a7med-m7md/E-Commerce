package com.bit.dtos;

import com.bit.persistence.entities.LaptopCategory;
import com.bit.util.enums.Rate;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class LaptopDTO {
    private UUID uuid;
    private byte[] imagByte;
    private LaptopCategory laptopCategory;
    private String name;
    private double price;
    private Rate rate;
}
