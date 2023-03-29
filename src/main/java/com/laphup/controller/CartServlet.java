package com.laphup.controller;

import com.google.gson.Gson;
import com.laphup.dtos.CartDTO;
import com.laphup.dtos.UserDto;
import com.laphup.persistence.entities.User;
import com.laphup.service.UserService;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@WebServlet(name = "CartServlet", value = "/cart")
public class CartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserDto user = (UserDto)session.getAttribute("userInfo");

        UserService userService = new UserService(request);
        User user1 = userService.getUserById(user.getUuid());

        System.out.println("=======");
        System.out.println(user1.getCard());
        ;

        List<CartDTO> carts = new ArrayList<>();

        user1.getCard().forEach(card -> {
            System.out.println(card.getLaptop().getUuidLaptop());
            System.out.println(card.getQuantity());
            CartDTO cartDTO = new CartDTO();
            cartDTO.setProductId(card.getLaptop().getUuidLaptop());
            cartDTO.setQuantity(card.getQuantity());
            carts.add(cartDTO);
        });

        System.out.println("===**===");

        Gson gson = new Gson();
        System.out.println(gson.toJson(carts));
        response.getWriter().println(gson.toJson(carts));
    }
}
