package com.laphup.persistence.daos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BaseDao<Table, ID, Name> {
    private EntityManager entityManager;

    public BaseDao(HttpServletRequest request) {
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
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(obj);
        transaction.commit();
        return obj;
    }

    public void delete(Table obj) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(obj);
        transaction.commit();
    }
}
