package com.laphup.service;

import com.laphup.persistence.entities.LaptopCategory;
import com.laphup.persistence.repositoryImp.LaptopCategoryRepoImp;
import jakarta.persistence.PersistenceException;
import jakarta.servlet.http.HttpServletRequest;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Set;

public class CategoryService {
    private LaptopCategoryRepoImp laptopCategoryRepoImp;
    public CategoryService(HttpServletRequest request){
        laptopCategoryRepoImp = new LaptopCategoryRepoImp(request);
    }

    public LaptopCategory saveCategory(String categoryName) throws PersistenceException {
        LaptopCategory category = new LaptopCategory();
        category.setCategoryName(categoryName);
        return laptopCategoryRepoImp.save(category);
    }

    public Set<LaptopCategory> getAllCategories(){
        return laptopCategoryRepoImp.getAll(new LaptopCategory());
    }

    public LaptopCategory getCategoryName(String name){
        return laptopCategoryRepoImp.getByCategoryName(name);
    }
}
