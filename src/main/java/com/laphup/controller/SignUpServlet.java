package com.laphup.controller;

import com.google.gson.Gson;
import com.laphup.dtos.UserDto;
import com.laphup.service.SingUpService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class SignUpServlet extends HttpServlet {
    boolean exist = true;

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
                exist = false;
            } else
                exist = true;
        }
        request.getServletContext().setAttribute("exist", exist);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println(request.getServletContext().getAttribute("exist"));
        boolean exist = (boolean) request.getServletContext().getAttribute("exist");
        if (exist) {
            UserDto user = checkExistence(request, response);
            System.out.println(user);
            SingUpService singUpService = new SingUpService(request);
            singUpService.register(user);
        }
    }

    public UserDto checkExistence(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Gson gson = new Gson();
        UserDto userDto = gson.fromJson(request.getReader(), UserDto.class);
        if (userDto != null)
            return userDto;
        else {
            RequestDispatcher rd = request.getRequestDispatcher("/siginUp.html");
            rd.include(request, response);
        }
        return null;
    }
}