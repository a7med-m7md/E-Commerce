package com.laphup.controller;

import com.google.gson.Gson;
import com.laphup.persistence.entities.User;
import com.laphup.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "UserServlet", value = "/users")
public class UsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserService(request);
        String id = request.getParameter("id");
        if(id == null){
            System.out.println("id: " + id);
            List<User> users = userService.getAllUsers();
//            Gson gson = new Gson();
//            String userListAsJSON = gson.toJson(users);
            request.setAttribute("users", users);
//            response.getWriter().write(userListAsJSON);
        }else {
            User user = userService.getUserById(UUID.fromString(id));
            request.setAttribute("user", user);
//            Gson gson = new Gson();
//            String userAsJSON = gson.toJson(user);
//            response.getWriter().write(userAsJSON);
        }
        request.getRequestDispatcher("view-users-table.jsp").forward(request, response);
    }
}
