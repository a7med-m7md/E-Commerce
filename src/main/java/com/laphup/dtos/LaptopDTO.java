package com.laphup.dtos;

import com.laphup.persistence.entities.LaptopCategory;
import com.laphup.util.enums.Rate;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Data
@Builder
public class LaptopDTO {
//    private byte[] imagByte;
//    private LaptopCategory laptopCategory;
    private String name;
    private String description;
    private double price;
//    private Rate rate;
    private int quantity;
}

