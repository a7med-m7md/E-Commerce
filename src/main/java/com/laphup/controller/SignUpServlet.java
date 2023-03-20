package com.laphup.controller;

import com.google.gson.Gson;
import com.laphup.controller.utility.JSPages;
import com.laphup.dtos.UserDto;
import com.laphup.service.SingUpService;
import com.laphup.util.enums.Role;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "signup", value = "/signup")
public class SignUpServlet extends HttpServlet {
    boolean exist = true;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter printWriter = response.getWriter();
        String email = request.getParameter("email");
        SingUpService singUpService = new SingUpService(request);
        if (email == null) {
            JSPages.SIGN_UP.forward(request, response);
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
        UserDto user = getUserObject(request, response);
        SingUpService singUpService = new SingUpService(request);
        singUpService.register(user);
        JSPages.SIGN_IN.forward(request, response);
//        }
    }

    public UserDto getUserObject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Gson gson = new Gson();
        System.out.println(request.getReader().toString());
        UserDto userDto = gson.fromJson(request.getReader(), UserDto.class);
        userDto.setRole(Role.USER);
        if (userDto != null)
            return userDto;
        else {
            JSPages.SIGN_UP.forward(request, response);
        }
        return null;
    }
}