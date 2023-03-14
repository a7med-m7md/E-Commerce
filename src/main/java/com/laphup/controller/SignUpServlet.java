package com.laphup.controller;

import com.laphup.dtos.UserDto;
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
    }

    public UserDto checkExistence(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDto user = null;
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String birthdate = request.getParameter("birthdate");
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(birthdate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        String job = request.getParameter("job");
        String address = request.getParameter("address");
        Gender gender = Gender.valueOf(request.getParameter("gender"));
        String interests = request.getParameter("interests");
        Long creditL = Long.valueOf(request.getParameter("creditL"));
        if (!(firstName.isEmpty() && lastName.isEmpty() && email.isEmpty() &&
                password.isEmpty() && job.isEmpty() && address.isEmpty() && interests.isEmpty()) && date != null &&
                gender != null && creditL != 0f) {
            user = new UserDto(date, firstName, lastName, gender, password, job, email, creditL, address, interests);
            return user;
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("/siginUp.html");
            rd.include(request, response);
        }
        return null;

    }
}