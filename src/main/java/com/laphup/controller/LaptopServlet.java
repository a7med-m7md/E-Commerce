package com.laphup.controller;

import com.laphup.persistence.daosImp.LaptopRepositoryImp;
import com.laphup.persistence.entities.Laptop;
import com.laphup.util.enums.SortBy;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class LaptopServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
//        int pageNumber = Integer.parseInt(req.getParameter("pageNumber"));
//        int count = Integer.parseInt(req.getParameter("count"));
//        String laptopCategory = req.getParameter("laptopCategory");
//        SortBy sortBy = SortBy.valueOf(req.getParameter("sortedBy"));
//        double minPrice = Double.parseDouble(req.getParameter("minPrice"));
//        double maxPrice = Double.parseDouble(req.getParameter("maxPrice"));
//        System.out.println(pageNumber+count+laptopCategory+sortBy+minPrice+maxPrice);
        //LaptopService laptopService = new LaptopService();
        //List<LaptopDTO> laptops = laptopService.getPage(pageNumber, count, laptopCategory, sortBy, minPrice, maxPrice);
        LaptopRepositoryImp laptopRepositoryImp = new LaptopRepositoryImp(req);
        List<Laptop> laptops = laptopRepositoryImp.getPage(1, 1, "hp", null, 0d, 10000d);
        Gson gson = new Gson();
        //String messageJson = gson.toJson(laptops);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        //out.println(messageJson);
        out.println("messageJson");
    }
}
