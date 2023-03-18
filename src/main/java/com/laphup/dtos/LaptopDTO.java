package com.laphup.dtos;

import com.laphup.persistence.entities.LaptopCategory;
import com.laphup.persistence.entities.LaptopImage;
import com.laphup.persistence.entities.OrderDetails;
import com.laphup.util.enums.Rate;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class LaptopDTO {
    private UUID uuid;
    private Set<byte[]> imagByteList = new HashSet<>();
    private String laptopCategory;
    private String name;
    private double price;
    private String rate;
    private int quantities;
    private String description;
}
