package com.laphup.controller;

import com.laphup.controller.utility.JSPages;
import com.laphup.persistence.entities.Laptop;
import com.laphup.service.LaptopService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "removeProductServlet", value = "/removeProduct")
public class RemoveProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uuid = request.getParameter("uuid");
        LaptopService laptopService = new LaptopService(request);
        Laptop laptop = laptopService.getNLaptop(UUID.fromString(uuid));
        laptopService.removeLaptop(laptop);
        response.sendRedirect("store");
    }
}
