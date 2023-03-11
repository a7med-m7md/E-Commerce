package com.laphup.Listeners;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;

public class RequestServlet implements ServletRequestListener {
    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        EntityManagerFactory entityManagerFactory = (EntityManagerFactory)sre.getServletContext().getContext("EntityManagerFactory");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        sre.getServletRequest().setAttribute("EntityManager",entityManager);
    }
}
