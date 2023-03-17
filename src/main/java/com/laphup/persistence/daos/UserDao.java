package com.laphup.persistence.daos;

import com.laphup.persistence.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Optional;

public class UserDao extends BaseDao<User, Integer, String> {

    private EntityManager entityManager;

    public UserDao(HttpServletRequest request) {
        super(request);
        if (request == null)
            System.out.println("UserDao");
        entityManager = (EntityManager) request.getAttribute("EntityManager");
    }

    public Optional<User> getByEmail(String email) {
        String queryString = "from User u where u.eMail = :email";
        Query q = entityManager.createQuery(queryString);
        q.setParameter("email", email);
        List<User> users = q.getResultList();
        if (users.isEmpty())
            return Optional.empty();
        else
            return Optional.of(users.get(0));
    }
}
