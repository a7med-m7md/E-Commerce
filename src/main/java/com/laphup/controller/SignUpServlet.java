package com.laphup.controller;

import com.laphup.dtos.UserDto;
import com.laphup.persistence.entities.User;
import com.laphup.service.SingUpService;
import com.laphup.util.enums.Gender;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SignUpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManagerFactory entityManagerFactory = (EntityManagerFactory) request.getAttribute("EntityManagerFactory");
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String birthdate = request.getParameter("birthdate");
        Date date= null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(birthdate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        String job = request.getParameter("job");
        String address = request.getParameter("address");
        Gender gender = Gender.valueOf(request.getParameter("gender"));
        String interests = request.getParameter("interests");
        System.out.println(request.getParameter("creditL"));
        Long creditL = Long.valueOf(request.getParameter("creditL"));


        UserDto user = new UserDto(date,firstName,lastName,gender,job,email,address,interests,creditL);
        SingUpService singUpService = new SingUpService(request);
        singUpService.register(user);
        System.out.println(firstName + " " + lastName + birthdate + gender + job + birthdate + address + interests + email + password);

    }
}