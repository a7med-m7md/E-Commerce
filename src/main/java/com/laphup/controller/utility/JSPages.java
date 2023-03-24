package com.laphup.controller.utility;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public enum JSPages {

    HOME_PAGE("/index.jsp"),
    ADD_LAPTOP("/add-laptop.jsp"),
    ADMIN_DISPLAY_USERS("/WEB-INF/view-users-table.jsp"),
    SIGN_IN("signIn.html"),
    SIGN_UP("siginUp.html"),
    STORE("store.jsp"),
    PRODUCT("product.jsp"),
    PROFILE("WEB-INF/profile.jsp");
    private final String path;
    JSPages(String path){
        this.path = path;
    }

    public String getPath(){
        return path;
    }

    public void forward(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(getPath()).forward(request, response);
    }
}
