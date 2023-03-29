package com.laphup.controller;

import com.google.gson.Gson;
import com.laphup.controller.utility.JSPages;
import com.laphup.dtos.*;
import com.laphup.persistence.entities.CompositeID.OrderDetailsId;
import com.laphup.persistence.entities.Laptop;
import com.laphup.persistence.entities.Order;
import com.laphup.persistence.entities.OrderDetails;
import com.laphup.persistence.entities.User;
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
import org.modelmapper.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

@WebServlet(value = "/checkout")
public class CheckOutServlet extends HttpServlet {

    List<LaptopDTO> laptops = new ArrayList<>();
    ModelMapper modelMapper = new ModelMapper();
    Order order;
    Map<UUID, Integer> map = new HashMap<>();

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
        Gson gson = new Gson();
//        Type founderListType = new TypeToken<ArrayList<LaptopDTO>>(){}.getType();
//        laptops = gson.fromJson(req.getReader(), founderListType);
        loadMap();
        totalPrice = 0;
        totalPrice = calculateCheckOut(req);

        System.out.println("Laptop Size" + laptops.size());
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

    public void loadMap() {
        laptops = AddToCardServlet.laptops;
        for (LaptopDTO laptopDTO : laptops) {
            Integer j = map.get(laptopDTO.getUuid());
            map.put(laptopDTO.getUuid(), (j == null) ? 1 : j + 1);
        }
    }

    public long calculateCheckOut(HttpServletRequest request) {
        AddToCardService addToCardService = new AddToCardService(request);
        for (Map.Entry<UUID, Integer> val : map.entrySet()) {
            Laptop laptopDTO = addToCardService.getLaptopByUuid2(val.getKey());
            totalPrice += (laptopDTO.getPrice() * val.getValue());
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

    public void updateCreditLimit(UUID uuid, HttpServletRequest request) {
        UserService userService = new UserService(request);
        User user = userService.getUserById(uuid);
        user.setCreditLimit(user.getCreditLimit() - totalPrice);
        userService.updateUserEntity(user);
    }

    public boolean checkQuantities(HttpServletRequest request) {
        for (Map.Entry<UUID, Integer> val : map.entrySet()) {
            AddToCardService addToCardService = new AddToCardService(request);
            Laptop laptop = addToCardService.getLaptopByUuid2(val.getKey());
            System.out.println("Quantity before :" + laptop.getQuantities());
            if (val.getValue() > laptop.getQuantities()) return false;
        }
        return true;
    }

    public void addOrder(HttpServletRequest request, long totalPrice) {
        OrderServices services = new OrderServices(request);
        HttpSession session = request.getSession();
        UserDto user = (UserDto) session.getAttribute("userInfo");
        User userEntity = modelMapper.map(user, User.class);
        Laptop laptopEntity;
        for (LaptopDTO laptopDTO : laptops) {
            Integer j = map.get(laptopDTO.getUuid());
            map.put(laptopDTO.getUuid(), (j == null) ? 1 : j + 1);
        }
        java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
        order = new Order(userEntity, date, totalPrice);
        for (Map.Entry<UUID, Integer> val : map.entrySet()) {
            System.out.println(val.getValue());
            UUID uuidLaptop = val.getKey();
            AddToCardService addToCardService = new AddToCardService(request);
            Laptop laptopDTO = addToCardService.getLaptopByUuid2(uuidLaptop);
            OrderDetailsId orderDetailsId = new OrderDetailsId(order.getOrderUuid(), laptopDTO.getUuidLaptop());
            OrderDetails orderDetails = new OrderDetails(orderDetailsId, order, laptopDTO, val.getValue());
            services.saveOrderDetails(orderDetails);
            laptopDTO.setQuantities(laptopDTO.getQuantities() - val.getValue());
            Laptop laptop1 = services.updateLaptops(laptopDTO);
        }
        services.checkOut(order);
        updateCreditLimit(user.getUuid(), request);
    }
}