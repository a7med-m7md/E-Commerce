package com.laphup.persistence.daosImp;

import jakarta.persistence.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

public class LaptopRepositoryImpTest {
//    @PersistenceContext(unitName = "movie-unit", type = PersistenceContextType.EXTENDED)
//    EntityManager entityManager;
    @Test
    @Tag("laptop")
    @Disabled
    void Get_Page_From_Laptop_Repository() {
        // Create a mock HttpServletRequest object
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        HttpServletRequest request = mock(HttpServletRequest.class);
        request.setAttribute("EntityManager", entityManager);

//        LaptopRepositoryImp laptopRepositoryImp = new LaptopRepositoryImp(request);
    }
}
