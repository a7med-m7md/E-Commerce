package com.laphup.persistence.repositoryImp;

import com.laphup.persistence.repository.BaseDao;
import com.laphup.persistence.entities.LaptopCategory;
import jakarta.servlet.http.HttpServletRequest;

import java.util.UUID;

public class LaptopCategoryDaoImp extends BaseDao<LaptopCategory, UUID, String> {
    private HttpServletRequest request;
    public LaptopCategoryDaoImp(HttpServletRequest request){
        super(request);
        this.request = request;
    }

//    public LaptopCategory getByCategory(){
//
//    }
}
