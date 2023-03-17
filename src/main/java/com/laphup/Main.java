package com.laphup;

import com.laphup.persistence.daosImp.LaptopRepositoryImp;
import com.laphup.persistence.entities.Laptop;
import com.laphup.persistence.entities.LaptopCategory;
import com.laphup.persistence.entities.OrderDetails;
import com.laphup.util.enums.SortBy;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Main {
    public static void main(String... args) {
        Main m = new Main();
        m.getPage(1, 1, "hp", null, 0d, 10000d);
    }

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu");
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    Boolean getPage(@NotNull int pageNumber, @NotNull int count, String laptopCategory, SortBy sortedBy, Double minPrice, Double maxPrice) {

        //Create CriteriaQuery and root table (laptop)
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Laptop> query_Laptop = criteriaBuilder.createQuery(Laptop.class);
        Root<Laptop> laptopRoot = query_Laptop.from(Laptop.class);

        //Join laptop table with laptopcategory table
        Join<Laptop, LaptopCategory> join = laptopRoot.join("laptopCategory");

        //Initiate predicates
        Predicate categoryPredicate = criteriaBuilder.equal(join.get("categoryName"), laptopCategory);
        Predicate pricePredicate = criteriaBuilder.between(laptopRoot.get("price"), minPrice, maxPrice);

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
        query_Laptop.where();

        ArrayList<Predicate> predicates = new ArrayList<>();

        //Execute the query according filters  layers
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
                .setFirstResult(0) // starting index of the first result
                .setMaxResults(10) // maximum number of results to retrieve
                .getResultList();

        return null;
    }

}
