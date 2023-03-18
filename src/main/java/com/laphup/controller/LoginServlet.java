package com.laphup.controller;


import com.laphup.dtos.LoginDTO;
import com.laphup.persistence.entities.User;
import com.laphup.service.SignInService;
import com.laphup.service.SingUpService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Optional;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/signIn.html");
        requestDispatcher.include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String password = request.getParameter("passwordL");
        String email = request.getParameter("emailL");
        SignInService signInService = new SignInService(request);
        if (email == null) {
            RequestDispatcher rd = request.getRequestDispatcher("/html.jsp");
            rd.forward(request, response);
        } else {
            LoginDTO loginDTO = new LoginDTO(email, password);
            Optional<User> user = signInService.logInto(loginDTO);
            if (!user.isEmpty()) {
                System.out.println("Login Success");
                HttpSession session = request.getSession();
                String fullName = user.get().getFName() + " " + user.get().getLName();
                session.setAttribute("uname", fullName);
                RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
                rd.include(request, response);
            } else
                System.out.println("Login failed");
        }
    }
}
