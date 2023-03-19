package com.laphup.controller;

import com.laphup.controller.utility.JSPages;
import com.laphup.dtos.UserDto;
import com.laphup.persistence.entities.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "home", value = "/home")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserDto user= (UserDto) session.getAttribute("userInfo");
        request.getSession().setAttribute("userInfo", user);
        System.out.println("NAME :: " + user);
        JSPages.HOME_PAGE.forward(request, response);
    }
}
