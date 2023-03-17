package com.laphup.persistence.repositoryImp;

import com.laphup.persistence.entities.LaptopCategory;
import com.laphup.persistence.repository.BaseRepo;
import com.laphup.persistence.repository.LaptopCategoryRepo;
import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpServletRequest;

import java.util.UUID;

public class LaptopCategoryRepoImp extends BaseRepo<LaptopCategory, UUID> implements LaptopCategoryRepo {
    private HttpServletRequest request;
    private EntityManager entityManager;
    public LaptopCategoryRepoImp(HttpServletRequest request){
        super(request);
        this.request = request;
        entityManager = (EntityManager) request.getAttribute("EntityManager");
    }

    @Override
    public LaptopCategory getByCategoryName(String name) {
        LaptopCategory category = entityManager.createNamedQuery("getByName", LaptopCategory.class)
                .setParameter("name", name)
                .getSingleResult();
        return category;
    }

}
