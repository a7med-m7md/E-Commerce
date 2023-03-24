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

    public LaptopDTO() {

    }

    public LaptopDTO(UUID uuid, Set<byte[]> imagByteList, String laptopCategory, String name, double price, String rate, int quantities, String description) {
        this.uuid = uuid;
        this.imagByteList = imagByteList;
        this.laptopCategory = laptopCategory;
        this.name = name;
        this.price = price;
        this.rate = rate;
        this.quantities = quantities;
        this.description = description;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Set<byte[]> getImagByteList() {
        return imagByteList;
    }

    public void setImagByteList(Set<byte[]> imagByteList) {
        this.imagByteList = imagByteList;
    }

    public String getLaptopCategory() {
        return laptopCategory;
    }

    public void setLaptopCategory(String laptopCategory) {
        this.laptopCategory = laptopCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public int getQuantities() {
        return quantities;
    }

    public void setQuantities(int quantities) {
        this.quantities = quantities;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
