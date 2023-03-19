package com.laphup.controller;


import com.laphup.controller.utility.CookieUtility;
import com.laphup.controller.utility.JSPages;
import com.laphup.dtos.LoginDTO;
import com.laphup.dtos.UserDto;
import com.laphup.persistence.entities.User;
import com.laphup.service.SignInService;
import com.laphup.service.SingUpService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/signin")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        UserDto user = (UserDto)session.getAttribute("userInfo");
        boolean isAlreadyLoggedIn = user != null;
        // 1. When he try to access /signin end point and he is already logged in
        if(isAlreadyLoggedIn){
            response.sendRedirect("home");
            return;
        }

        boolean isLoggedInUsingCookies = loggedInWithCookie(request);

        if(isLoggedInUsingCookies){
            response.sendRedirect("home");
            return;
        }


        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/signIn.html");
        requestDispatcher.include(request, response);
    }

    private boolean loggedInWithCookie(HttpServletRequest request) {
        Optional<Cookie> userCookie = CookieUtility.getCookie(request.getCookies(), "user_remember_cookie");
        if(userCookie.isPresent()){
            String userCookieVal = userCookie.get().getValue();
            String userEmailVal = userCookieVal.split("=")[0];
            String userPasswordVal = userCookieVal.split("=")[1];
            //todo Should Refactor
            SignInService signInService = new SignInService(request);
            LoginDTO loginDTO = new LoginDTO(userEmailVal, userPasswordVal);
            Optional<UserDto> user = signInService.logInto(loginDTO);
            //==
            if(user.isPresent()){
                return true;
            }
        }
        return false;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // GET user inputs
        String password = request.getParameter("passwordL");
        String email = request.getParameter("emailL");

        if (email == null || password == null) {
            JSPages.SIGN_IN.forward(request, response);
            return;
        }
        // todo Should refactor
        SignInService signInService = new SignInService(request);
        LoginDTO loginDTO = new LoginDTO(email, password);
        Optional<UserDto> user = signInService.logInto(loginDTO);
        // ==
        if (!user.isEmpty() && userFounded(user, loginDTO)) {
                System.out.println("Login Success");
                HttpSession session = request.getSession();
//                String fullName = user.get().getfName() + " " + user.get().getlName();
                session.setAttribute("userInfo", user.get());
                SaveSession(loginDTO, request, response);
                response.sendRedirect("home");
                return;
            }

            System.out.println("Login failed");
            JSPages.SIGN_IN.forward(request, response);
    }

    public void SaveSession(LoginDTO loginDTO, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie cookie = new Cookie("user_remember_cookie", loginDTO.geteMail() + "=" + loginDTO.getPassword());
//        Cookie pas = new Cookie("password", loginDTO.getPassword());
        cookie.setMaxAge(7 * 24 * 60 * 60);
        response.addCookie(cookie);
//        response.addCookie(pas);
        checkExistence(loginDTO, request, response);
    }

    public void checkExistence(LoginDTO loginDTO, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null) {
//            RequestDispatcher rd = request.getRequestDispatcher("/signIn.html");
//            rd.forward(request, response);
        } else {
            String loggedIn = (String) session.getAttribute("loginDTO");
            if (loggedIn == null) {
                session = request.getSession(true);
                session.setAttribute("loginDTO", loginDTO);
//                RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
//                rd.forward(request, response);
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
