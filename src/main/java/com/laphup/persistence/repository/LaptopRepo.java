package com.laphup.persistence.repository;

import com.laphup.dtos.LaptopDTO;
import com.laphup.persistence.entities.Laptop;
import com.laphup.util.enums.SortBy;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface LaptopRepo {
    public List<Laptop> getAll(HttpServletRequest requestServlet, int pageNumber, int count, String laptopCategory,
                               SortBy sortedBy, double minPrice, double maxPrice);
    void create(HttpServletRequest requestServlet, LaptopDTO laptop);
    // I want to convert images List<String>
    // 1. to be in server + 2. Save their path in db
}
