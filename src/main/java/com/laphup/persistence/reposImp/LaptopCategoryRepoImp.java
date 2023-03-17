package com.laphup.persistence.reposImp;

import com.laphup.persistence.repository.BaseRepo;
import com.laphup.persistence.entities.LaptopCategory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.UUID;

public class LaptopCategoryRepoImp extends BaseRepo<LaptopCategory, UUID> {
    private HttpServletRequest request;
    private EntityManager entityManager;
    public LaptopCategoryRepoImp(HttpServletRequest request){
        super(request);
        this.request = request;
        this.entityManager = (EntityManager) request.getAttribute("EntityManager");
    }

    public LaptopCategory getByName(String categoryName){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<LaptopCategory> query = criteriaBuilder.createQuery(LaptopCategory.class);
        Root<LaptopCategory> categoryRoot = query.from(LaptopCategory.class);

        query.where(criteriaBuilder.equal(categoryRoot.get("categoryName"),categoryName));
        query.select(categoryRoot);

        List<LaptopCategory> laptopCategory = entityManager.createQuery(query).getResultList();

        if(laptopCategory.size()>0)
            return laptopCategory.get(0);
        return null;
    }
}
