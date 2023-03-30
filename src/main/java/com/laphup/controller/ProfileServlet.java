package com.laphup.controller;

import com.google.gson.Gson;
import com.laphup.controller.utility.JSPages;
import com.laphup.dtos.LoginDTO;
import com.laphup.dtos.UserDto;
import com.laphup.persistence.entities.User;
import com.laphup.service.UserService;
import com.laphup.util.enums.Gender;
import com.laphup.util.enums.Role;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@WebServlet(name = "profileServlet", value = "/profile")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserDto user = (UserDto) session.getAttribute("userInfo");
        System.out.println("User:: " + user.getfName());
//        request.getSession().setAttribute("userInfo", user);
        JSPages.PROFILE.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("PPPPOOOOOSSSTT");
        // Extract Info from user request
        try {
            String uuid = request.getParameter("uuid");
            String firstName = request.getParameter("firstname");
            String lastName = request.getParameter("lastname");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String address = request.getParameter("address");
            String job = request.getParameter("job");
            Gender gender = request.getParameter("gender").equals("Male") ? Gender.MALE : Gender.FEMALE;
            String birthday = request.getParameter("birthday");
            int creditLimit = 0;
            if(request.getParameter("creditlimit") != null){
                creditLimit = Integer.parseInt(request.getParameter("creditlimit"));
            }
            UserDto currentUser = (UserDto)request.getSession().getAttribute("userInfo");
            System.out.println("ROLE:: " + currentUser.getRole());
            System.out.println("DATE :: T" + currentUser.getBirthDay());
            // Update it
            UserService userService = new UserService(request);
//            System.out.println("Birthday:: " + birthday);
            UserDto userDto = null;


            userDto = new UserDto(formatDate(birthday), firstName, lastName, gender, password, job, email, creditLimit, address, "");

            userDto.setUuid(UUID.fromString(uuid));
            userDto.setRole(currentUser.getRole());

            userService.updateUser(userDto);

            // Update information in session
            if (request.getSession().getAttribute("userInfo") != null)
                request.getSession().setAttribute("userInfo", userDto);

            // Redirect with successful message
            request.setAttribute("successMSG", "updated");
            JSPages.PROFILE.forward(request, response);
            System.out.println("DATE   " + userDto.getBirthDay());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

//            UserDto.builder()
//                    .fName(firstName)
//                    .lName(lastName)
//                    .eMail(email)
//                    .password(password)
//                    .address(address)
//                    .job(job)
//                    .gender(gender.equals("Male") ? Gender.MALE: Gender.FEMALE)
//                    .birthDay(new Date(birthday))
//                    .creditLimit(creditLimit)
//                    .build();
    }

    public Date formatDate(String date) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.parse(date);
    }
}
