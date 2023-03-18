package com.laphup.controller;


import com.laphup.dtos.LoginDTO;
import com.laphup.dtos.UserDto;
import com.laphup.persistence.entities.User;
import com.laphup.service.SignInService;
import com.laphup.service.SingUpService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

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
            RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
            rd.forward(request, response);
        } else {
            LoginDTO loginDTO = new LoginDTO(email, password);
            Optional<UserDto> user = signInService.logInto(loginDTO);
            if (!user.isEmpty()) {
                if (userFounded(user, loginDTO)) {
                    System.out.println("Login Success");
                    HttpSession session = request.getSession();
                    String fullName = user.get().getfName() + " " + user.get().getlName();
                    session.setAttribute("uname", fullName);
                    SaveSession(loginDTO, request, response);
                    RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
                    rd.include(request, response);
                } else
                    System.out.println("Login failed");
            } else
                System.out.println("Login failed");
        }
    }

    public void SaveSession(LoginDTO loginDTO, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie ck = new Cookie("email", loginDTO.geteMail());
        Cookie pas = new Cookie("password", loginDTO.getPassword());
        response.addCookie(ck);
        response.addCookie(pas);
        checkExistence(loginDTO, request, response);
    }

    public void checkExistence(LoginDTO loginDTO, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null) {
            RequestDispatcher rd = request.getRequestDispatcher("/signIn.html");
            rd.forward(request, response);
        } else {
            String loggedIn = (String) session.getAttribute("loginDTO");
            if (loggedIn == null) {
                session = request.getSession(true);
                session.setAttribute("loginDTO", loginDTO);
                RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
                rd.forward(request, response);
            }
        }
    }

    public boolean userFounded(Optional<UserDto> userDto, LoginDTO loginDTO) {

        if (userDto.get().geteMail().equals(loginDTO.geteMail()) &&
                userDto.get().getPassword().equals(loginDTO.getPassword())) {
            return true;
        }
        return false;
    }
}
