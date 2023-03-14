package com.laphup.controller;

import com.google.gson.Gson;
import com.laphup.dtos.UserDto;
import com.laphup.persistence.entities.User;
import com.laphup.service.SingUpService;
import com.laphup.util.enums.Gender;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SignUpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter printWriter = response.getWriter();
        String email = request.getParameter("email");
        SingUpService singUpService = new SingUpService(request);
        if (email == null) {
            RequestDispatcher rd = request.getRequestDispatcher("/siginUp.html");
            rd.forward(request, response);
        } else {
            if (singUpService.isNewUser(email)) {
                System.out.println("This Mail Exist");
                printWriter.print("Exist");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDto user = checkExistence(request, response);
        System.out.println(user);
        SingUpService singUpService = new SingUpService(request);
        singUpService.register(user);
        response.sendRedirect("/index.jsp");
    }

    public UserDto checkExistence(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Gson gson = new Gson();
        UserDto userDto = gson.fromJson(request.getReader(), UserDto.class);
        if(userDto!=null)
        return userDto;
        else {
            RequestDispatcher rd = request.getRequestDispatcher("/siginUp.html");
            rd.include(request, response);
        }
        return null;
    }
}