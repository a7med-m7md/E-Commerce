package com.laphup.controller;

import com.laphup.service.LaptopService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "pagesCount", value = "/pagesCount")
public class LaptopCountsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LaptopService laptopService = new LaptopService(request);
        Long count = laptopService.getLaptopCount();

        PrintWriter out = response.getWriter();
        out.print(count);
    }
}
