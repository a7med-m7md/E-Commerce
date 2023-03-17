package com.laphup.service;

import com.laphup.persistence.entities.LaptopCategory;
import com.laphup.persistence.repositoryImp.LaptopCategoryRepoImp;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Set;

public class CategoryService {
    private LaptopCategoryRepoImp laptopCategoryRepoImp;
    public CategoryService(HttpServletRequest request){
        laptopCategoryRepoImp = new LaptopCategoryRepoImp(request);
    }

    public void saveCategory(String categoryName){
        LaptopCategory category = new LaptopCategory();
        category.setCategoryName(categoryName);
        laptopCategoryRepoImp.save(category);
    }

    public Set<LaptopCategory> getAllCategories(){
        return laptopCategoryRepoImp.getAll(new LaptopCategory());
    }
}
