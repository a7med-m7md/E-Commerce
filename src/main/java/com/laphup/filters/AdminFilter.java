package com.laphup.filters;

import com.laphup.dtos.UserDto;
import com.laphup.util.enums.Role;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(filterName = "AdminFilter", urlPatterns = {"/addLaptop", "/users"})
public class AdminFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        UserDto user = (UserDto) req.getSession().getAttribute("userInfo");
        if(user.getRole() != Role.ADMIN){
            res.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied.");
            return;
        }
        chain.doFilter(request, response);
    }
}
