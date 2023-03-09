package com.bit.controller;

import com.bit.dtos.LaptopDTO;
import com.bit.persistence.entities.LaptopCategory;
import com.bit.service.LaptopService;
import com.bit.util.enums.SortBy;
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
        int pageNumber = Integer.parseInt(req.getParameter("pageNumber"));
        int count = Integer.parseInt(req.getParameter("count"));
        String laptopCategory = req.getParameter("laptopCategory");
        SortBy sortBy = SortBy.valueOf(req.getParameter("sortedBy"));
        double minPrice = Double.parseDouble(req.getParameter("minPrice"));
        double maxPrice = Double.parseDouble(req.getParameter("maxPrice"));

        LaptopService laptopService = new LaptopService();
        List<LaptopDTO> laptops = laptopService.getPage(pageNumber, count, laptopCategory, sortBy, minPrice, maxPrice);

        response.setContentType("text/html");
        PrintWriter out  = response.getWriter();
        out.println(laptops.get(0).getName());
    }
}
