package com.laphup.controller;

import com.google.gson.Gson;
import com.laphup.persistence.entities.LaptopCategory;
import com.laphup.service.CategoryService;
import jakarta.persistence.PersistenceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;

@WebServlet(name = "categoryServlet", value = "/category")
public class CategoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CategoryService categoryService = new CategoryService(request);
        Gson gson = new Gson();
        String categoriesAsJson = gson.toJson(categoryService.getAllCategories());
//        request.setAttribute("categoryList", categoryService.getAllCategories());
        response.getWriter().write(categoriesAsJson);
//        request.getRequestDispatcher("add-laptop.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String categoryName = request.getParameter("category-name");
            CategoryService categoryService = new CategoryService(request);
            LaptopCategory category = categoryService.saveCategory(categoryName);
            Gson gson = new Gson();
            String categoryAsJson = gson.toJson(category);
            response.getWriter().write(categoryAsJson);
        }catch (PersistenceException e){
            System.out.println("Can't add category");
            response.getWriter().write("{\"error\": \"true\"}");
        }
    }

}
