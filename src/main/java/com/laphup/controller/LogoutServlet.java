package com.laphup.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet(name = "logoutServlet", value = "/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if(session != null){
            session.removeAttribute("userInfo");
            session.invalidate();
            Cookie[] cookies = request.getCookies();
            if(cookies != null){
                for(Cookie cookie: cookies){
                    if(cookie.getValue().equals("user_remember_cookie")){
                        cookie.setValue("");
                        response.addCookie(cookie);
                    }
                }
            }
            response.sendRedirect("home");
        }
    }
}
