package com.laphup.controller;

import com.google.gson.Gson;
import com.laphup.dtos.*;
import com.laphup.persistence.entities.CompositeID.OrderDetailsId;
import com.laphup.persistence.entities.OrderDetails;
import com.laphup.service.AddToCardService;
import com.laphup.service.OrderServices;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.*;

@WebServlet(value = "/checkout")
public class CheckOutServlet extends HttpServlet {

    List<LaptopDTO> laptops = new ArrayList<>();
    Set<OrderDetailsDTO> orderDetailsDTOSet = new HashSet<>();
    long totalPrice = 0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        UUID uuidstr = UUID.fromString(req.getParameter("uuid"));
        AddToCardService addToCardService = new AddToCardService(req);
        LaptopDTO laptop = addToCardService.getLaptopByUuid(uuidstr);
        String laptopJson = gson.toJson(laptop);
        resp.getWriter().println(laptopJson);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (checkQuantities(laptops, req)) {
            double totalPrice = calculateCheckOut(laptops, req);
            resp.getWriter().println(totalPrice);
        } else {
            resp.getWriter().println("Our Store Cant Fit Your Order");
        }
    }

    public long calculateCheckOut(List<LaptopDTO> laptops, HttpServletRequest request) {


        for (LaptopDTO laptopDTO : laptops) {
            totalPrice += (laptopDTO.getPrice() * laptopDTO.getQuantities());
        }
        return totalPrice;
    }

    public boolean checkQuantities(List<LaptopDTO> laptops, HttpServletRequest request) {
        OrderServices services = new OrderServices(request);
        OrderDTO orderDTO = null;
        HttpSession session = request.getSession();
        UserDto user = (UserDto) session.getAttribute("userInfo");
        for (LaptopDTO laptopDTO : laptops) {
            if (!services.updateLaptops(laptopDTO)) {
                orderDetailsDTOSet.add(new OrderDetailsDTO(orderDTO, laptopDTO, laptopDTO.getQuantities()));
                return false;
            }
        }
        java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
        orderDTO = new OrderDTO(user, date, totalPrice);
        services.checkOut(orderDTO);

        return false;
    }
}
