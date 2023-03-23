package com.laphup.controller;

import com.google.gson.Gson;
import com.laphup.controller.utility.JSPages;
import com.laphup.dtos.UserDto;
import com.laphup.persistence.entities.User;
import com.laphup.util.enums.Role;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "profileServlet", value = "/profile")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserDto user= (UserDto) session.getAttribute("userInfo");
        System.out.println("User:: " + user.getfName());
//        request.getSession().setAttribute("userInfo", user);
        JSPages.PROFILE.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String firstName = request.getParameter("firstname");
            String lastName = request.getParameter("lastname");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String address = request.getParameter("address");
            String job = request.getParameter("job");
            String gender = request.getParameter("gender");
            String birthday = request.getParameter("birthday");
            String creditLimit = request.getParameter("creditlimit");

        }catch (Exception ex){}
        JSPages.PROFILE.forward(request, response);
    }
}
