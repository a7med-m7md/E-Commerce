package com.laphup.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.laphup.dtos.CartDTO;
import com.laphup.dtos.UserDto;
import com.laphup.persistence.entities.Card;
import com.laphup.persistence.entities.CompositeID.CardId;
import com.laphup.persistence.entities.Laptop;
import com.laphup.persistence.entities.User;
import com.laphup.persistence.repository.BaseRepo;
import com.laphup.persistence.repositoryImp.CardRepo;
import com.laphup.service.LaptopService;
import com.laphup.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "logoutServlet", value = "/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if(session != null){
            session.removeAttribute("userInfo");
            session.invalidate();
            Cookie[] cookies = request.getCookies();
            if(cookies != null){
                for(Cookie cookie: cookies){
                    if(cookie.getName().equals("user_remember_cookie")){
                        cookie.setValue("");
                        cookie.setMaxAge(0);
                        System.out.println("logoutt");
                        response.addCookie(cookie);
                    }
                }
            }
            response.sendRedirect("home");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("POST Logout");
        String cartLocalStorageData = request.getParameter("data");
        Gson gson = new Gson();
        Type cardListType = new TypeToken<List<CartDTO>>() {}.getType();
        List<CartDTO> cards = gson.fromJson(cartLocalStorageData, cardListType);
        System.out.println(cards.size());

//        BaseRepo<Card, CardId> cardCardIdBaseRepo = new BaseRepo<>(request);
        for (CartDTO cartDTO: cards) {
            System.out.println(cartDTO);

            Card card = new Card();
            UserDto userDto = (UserDto) request.getSession().getAttribute("userInfo");

            CardId cardId = new CardId(userDto.getUuid(), cartDTO.getProductId());
            card.setCardId(cardId);


            card.setQuantity(cartDTO.getQuantity());
            CardRepo cardRepo = new CardRepo(request);
            LaptopService laptopService = new LaptopService(request);
            Laptop laptop = laptopService.getNLaptop(cartDTO.getProductId());
            System.out.println("Getting : " + laptop.getName());
            card.setLaptop(laptop);
//            System.out.println(new Gson().toJson(card));

            UserService userService = new UserService(request);
            User user= userService.getUserById(userDto.getUuid());
            card.setUser(user);
            cardRepo.saveCart(card);
            System.out.println("END ");
        }
    }
}
