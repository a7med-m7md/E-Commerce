package com.bit.persistence.daos;

import com.bit.persistence.entities.LaptopCategory;
import com.bit.persistence.entities.Laptop;
import com.bit.util.enums.SortBy;

import java.util.List;

public interface LaptopDao {
    public List<Laptop> getAll(int pageNumber, int count, String laptopCategory,
                               SortBy sortedBy, double minPrice, double maxPrice);
}
