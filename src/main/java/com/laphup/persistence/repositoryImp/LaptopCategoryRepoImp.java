package com.laphup.persistence.repositoryImp;

import com.laphup.persistence.repository.BaseDao;
import com.laphup.persistence.entities.LaptopCategory;
import jakarta.servlet.http.HttpServletRequest;

import java.util.UUID;

public class LaptopCategoryRepoImp extends BaseDao<LaptopCategory, UUID, String> {
    private HttpServletRequest request;
    public LaptopCategoryRepoImp(HttpServletRequest request){
        super(request);
        this.request = request;
    }

//    public LaptopCategory getByCategory(){
//
//    }
}