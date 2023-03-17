package com.laphup.persistence.reposImp;

import com.laphup.persistence.repository.BaseRepo;
import com.laphup.persistence.entities.Laptop;
import com.laphup.persistence.entities.LaptopCategory;
import com.laphup.util.enums.SortBy;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LaptopRepoImp extends BaseRepo<LaptopCategory, UUID> {
    private HttpServletRequest request;
    private EntityManager entityManager;
    private CriteriaBuilder criteriaBuilder;
    public LaptopRepoImp(HttpServletRequest request){
        super(request);
        this.request = request;
        this.entityManager = (EntityManager) request.getAttribute("EntityManager");
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public List<Laptop> getPage(@NotNull int pageNumber, @NotNull int count, String laptopCategory, SortBy sortedBy, Double minPrice, Double maxPrice) {

        //Create CriteriaQuery and root table (laptop)
        CriteriaQuery<Laptop> query_Laptop = criteriaBuilder.createQuery(Laptop.class);
        Root<Laptop> laptopRoot = query_Laptop.from(Laptop.class);

        //Join laptop table with laptopcategory table
        Join<Laptop, LaptopCategory> join = laptopRoot.join("laptopCategory");

        //Initiate predicates
        Predicate categoryPredicate = criteriaBuilder.equal(join.get("categoryName"), laptopCategory);
        Predicate pricePredicate = criteriaBuilder.between(laptopRoot.get("price"), minPrice, maxPrice);

        //Choose which orderby
        Order order = null;
        if (sortedBy == SortBy.RATE)
            order = criteriaBuilder.asc(laptopRoot.get("rate"));
        else if (sortedBy == SortBy.PRICE)
            order = criteriaBuilder.asc(laptopRoot.get("price"));
//        else if (sortedBy == SortBy.PURCHASINGNUMBER)
//            order = criteriaBuilder.asc(laptopRoot.get("price"));
        else {
            order = criteriaBuilder.asc(laptopRoot.get("rate"));
        }

        //Execute the query according filters  layers
        ArrayList<Predicate> predicates = new ArrayList<>();
        if (laptopCategory != null)
            predicates.add(categoryPredicate);
        if (minPrice != null && maxPrice != null)
            predicates.add(pricePredicate);

        // Build the where clause dynamically based on the predicates
        Predicate whereClause = criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        query_Laptop.select(laptopRoot)
                .where(whereClause)
                .orderBy(order);

        //Get Result
        List<Laptop> laptopList = entityManager.createQuery(query_Laptop)
                .setFirstResult((pageNumber-1)*count) // starting index of the first result
                .setMaxResults(count) // maximum number of results to retrieve
                .getResultList();

        return laptopList;
    }
}
