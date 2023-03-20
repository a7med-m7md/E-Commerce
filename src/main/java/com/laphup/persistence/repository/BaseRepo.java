package com.laphup.persistence.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BaseRepo<Table,ID> {
    private EntityManager entityManager;

    public BaseRepo(HttpServletRequest request) {
        if (request == null)
            System.out.println("Yarabbbbbbbbbbbb");
        entityManager = (EntityManager) request.getAttribute("EntityManager");
    }

    public Set<Table> getAll(Table obj) {
        String jpql = "SELECT o FROM " + obj.getClass().getSimpleName() + " o";
        Query query = entityManager.createQuery(jpql, List.class);
        return (Set<Table>) query.getResultStream().collect(Collectors.toSet());
    }

    public Table getById(ID id) {
        Table table = (Table) entityManager.find(id.getClass(), id);
        return table;
    }

    public Table save(Table obj) {
        System.out.println("in loin333333333");
        EntityTransaction transaction = entityManager.getTransaction();
        System.out.println("in loin333333333");
        try {
            transaction.begin();
            entityManager.persist(obj);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }
        return obj;
    }

    public void delete(Table obj) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.remove(obj);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }
    }
}
