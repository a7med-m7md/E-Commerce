package com.laphup.persistence.repository;

import com.laphup.persistence.entities.Laptop;
import com.laphup.util.enums.SortBy;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface LaptopRepository {
    public List<Laptop> getAll(HttpServletRequest requestServlet, int pageNumber, int count, String laptopCategory,
                               SortBy sortedBy, double minPrice, double maxPrice);
}
