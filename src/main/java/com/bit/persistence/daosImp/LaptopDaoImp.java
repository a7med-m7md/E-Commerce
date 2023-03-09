package com.bit.persistence.daosImp;

import com.bit.persistence.daos.LaptopDao;
import com.bit.persistence.entities.LaptopCategory;
import com.bit.persistence.entities.Laptop;
import com.bit.util.enums.Rate;
import com.bit.util.enums.SortBy;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LaptopDaoImp implements LaptopDao {
    @Override
    public List<Laptop> getAll(int pageNumber, int count, String laptopCategory, SortBy sortedBy, double minPrice, double maxPrice) {
        List<Laptop> laptops = new ArrayList<>();

        Laptop laptop = new Laptop();
        laptop.setName("Hp Envi");
        laptop.setPrice(20000);
        laptop.setRate(Rate.FIVE);
        laptop.setUuid(UUID.randomUUID());
//        laptop.setLaptopCategory(new LaptopCategory());
        laptop.setPurchasingCount(5);
        laptop.setImagPath("dar.png");

        laptops.add(laptop);
        return laptops;
    }
}
