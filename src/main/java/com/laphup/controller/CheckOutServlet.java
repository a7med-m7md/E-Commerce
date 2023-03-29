package com.laphup.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.laphup.controller.utility.JSPages;
import com.laphup.dtos.*;
import com.laphup.persistence.entities.*;
import com.laphup.persistence.entities.CompositeID.OrderDetailsId;
import com.laphup.service.AddToCardService;
import com.laphup.service.OrderServices;
import com.laphup.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.*;

@WebServlet(value = "/checkout")
public class CheckOutServlet extends HttpServlet {
    ModelMapper modelMapper = new ModelMapper();
    List<CartDTO> cartDTOS;
    long totalPrice = 0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        UUID uuidstr = UUID.fromString(req.getParameter("uuid"));
        AddToCardService addToCardService = new AddToCardService(req);
        LaptopDTO laptop = addToCardService.getLaptopByUuid(uuidstr);
        String laptopJson = gson.toJson(laptop);
        resp.getWriter().print(laptopJson);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Gson gson = new Gson();
        Type founderListType = new TypeToken<ArrayList<CartDTO>>() {
        }.getType();
        cartDTOS = gson.fromJson(req.getReader(), founderListType);
        System.out.println("Size : " + cartDTOS.size());
        totalPrice = calculateCheckOut(req);
        resp.getWriter().print(presistOrder(req));
    }

    public String presistOrder(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserDto user = (UserDto) session.getAttribute("userInfo");
        if (!checkQuantities(request)) {
            return "Out";
        } else if (!checkLimitationCredit(user.getUuid(), request)) {
            return "more";
        } else {
            addOrder(request, totalPrice);
            return "Success";
        }
    }

    public long calculateCheckOut(HttpServletRequest request) {
        totalPrice = 0;
        AddToCardService addToCardService = new AddToCardService(request);
        for (CartDTO cartDTO : cartDTOS) {
            Laptop laptopDTO = addToCardService.getLaptopByUuid2(cartDTO.getProductId());
            totalPrice += (laptopDTO.getPrice() * cartDTO.getQuantity());
        }
        return totalPrice;
    }

    public boolean checkLimitationCredit(UUID userUuid, HttpServletRequest request) {
        UserService userService = new UserService(request);
        User user = userService.getUserById(userUuid);
        System.out.println("getCreditLimit :" + user.getCreditLimit() + "Total Price : " + totalPrice);
        if (totalPrice > user.getCreditLimit()) return false;
        return true;

    }

    public boolean checkQuantities(HttpServletRequest request) {
        for (CartDTO cartDTO : cartDTOS) {
            AddToCardService addToCardService = new AddToCardService(request);
            Laptop laptop = addToCardService.getLaptopByUuid2(cartDTO.getProductId());
            System.out.println("Quantity before :" + laptop.getQuantities());
            if (cartDTO.getQuantity() > laptop.getQuantities()) return false;
        }
        return true;
    }

    public void addOrder(HttpServletRequest request, long totalPrice) {
        OrderServices services = new OrderServices(request);
        HttpSession session = request.getSession();
        UserDto user = (UserDto) session.getAttribute("userInfo");
        User userEntity = modelMapper.map(user, User.class);
        java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
        Order order = new Order(userEntity, date, totalPrice);
        UserService userService = new UserService(request);
        for (CartDTO cartDTO : cartDTOS) {
            UUID uuidLaptop = cartDTO.getProductId();
            AddToCardService addToCardService = new AddToCardService(request);
            Laptop laptopDTO = addToCardService.getLaptopByUuid2(uuidLaptop);
            OrderDetailsId orderDetailsId = new OrderDetailsId(order.getOrderUuid(), laptopDTO.getUuidLaptop());
            OrderDetails orderDetails = new OrderDetails(orderDetailsId, order, laptopDTO, cartDTO.getQuantity());
            services.saveOrderDetails(orderDetails);
            laptopDTO.setQuantities(laptopDTO.getQuantities() - cartDTO.getQuantity());
            Laptop laptop1 = services.updateLaptops(laptopDTO);
        }
        services.checkOut(order);
        updateCreditLimit(user.getUuid(), request);
    }

    public void updateCreditLimit(UUID uuid, HttpServletRequest request) {
        UserService userService = new UserService(request);
        User user = userService.getUserById(uuid);
        user.setCreditLimit(user.getCreditLimit() - totalPrice);
        userService.updateUserEntity(user);
    }
}