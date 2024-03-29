package com.laphup.Listeners;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //ServletContextListener.super.contextInitialized(sce);
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu");
        sce.getServletContext().setAttribute("EntityManagerFactory",entityManagerFactory);
    }
}
