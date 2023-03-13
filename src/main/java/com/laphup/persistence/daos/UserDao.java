package com.laphup.persistence.daos;

import com.laphup.persistence.entities.RegisterEntity;
import com.laphup.persistence.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class UserDao extends BaseDao<User, Integer, String> {

    private EntityManager entityManager;

    public UserDao(HttpServletRequest request) {
        super(request);
        if (request == null)
            System.out.println("UserDao");
        entityManager = (EntityManager) request.getAttribute("EntityManager");
    }

    public User getByEmail(String email) {
        User user = (User) entityManager.find(User.class, email);
        return user;
    }


}
