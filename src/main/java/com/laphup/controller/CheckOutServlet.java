package com.laphup.controller;

import com.google.gson.Gson;
import com.laphup.dtos.*;
import com.laphup.persistence.entities.CompositeID.OrderDetailsId;
import com.laphup.persistence.entities.Laptop;
import com.laphup.persistence.entities.Order;
import com.laphup.persistence.entities.OrderDetails;
import com.laphup.persistence.entities.User;
import com.laphup.service.AddToCardService;
import com.laphup.service.OrderServices;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;

import java.io.IOException;
import java.util.*;

@WebServlet(value = "/checkout")
public class CheckOutServlet extends HttpServlet {

    List<LaptopDTO> laptops = new ArrayList<>();
    Set<OrderDetails> orderDetailsDTOSet = new HashSet<>();
    ModelMapper modelMapper = new ModelMapper();
    Order order;
    Map<UUID, Integer> map;
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
        map = new HashMap<>();
//        totalPrice = 0;
        laptops = AddToCardServlet.laptops;
//        calculateCheckOut(laptops, req);
        System.out.println("Laptop Size" + laptops.size());
        if (checkQuantities(laptops, req)) {
            resp.getWriter().println(totalPrice);
        } else {
            resp.getWriter().println("Our Store Cant Fit Your Order");
        }
    }

//    public long calculateCheckOut(List<LaptopDTO> laptops, HttpServletRequest request) {
//        for (LaptopDTO laptopDTO : laptops) {
//            totalPrice += (laptopDTO.getPrice() * laptopDTO.getQuantities());
//        }
//        return totalPrice;
//    }

    public boolean checkQuantities(List<LaptopDTO> laptops, HttpServletRequest request) {
         totalPrice = 0;
        OrderServices services = new OrderServices(request);
        HttpSession session = request.getSession();
        UserDto user = (UserDto) session.getAttribute("userInfo");
        User userEntity = modelMapper.map(user, User.class);
        Laptop laptopEntity;
        for (LaptopDTO laptopDTO : laptops) {
            Integer j = map.get(laptopDTO.getUuid());
            totalPrice += laptopDTO.getPrice();
            map.put(laptopDTO.getUuid(), (j == null) ? 1 : j + 1);
        }
        java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
        order = new Order(userEntity, date, totalPrice);
        for (Map.Entry<UUID, Integer> val : map.entrySet()) {
            UUID uuidLaptop = val.getKey();
            AddToCardService addToCardService = new AddToCardService(request);
            Laptop laptopDTO = addToCardService.getLaptopByUuid2(uuidLaptop);
            // if (!services.updateLaptops(laptopEntity)) {
            OrderDetailsId orderDetailsId = new OrderDetailsId(order.getOrderUuid(), laptopDTO.getUuidLaptop());
            OrderDetails orderDetails = new OrderDetails(orderDetailsId, order, laptopDTO, val.getValue());
//            orderDetailsDTOSet.add(orderDetails);
            services.saveOrderDetails(orderDetails);
        }
        services.checkOut(order);
        return false;
    }
}
