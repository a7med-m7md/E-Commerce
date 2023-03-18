package com.laphup.persistence.repository;

import com.laphup.persistence.entities.LaptopCategory;

public interface LaptopCategoryRepo {
    LaptopCategory getByCategoryName(String name);
}
