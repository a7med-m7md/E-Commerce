package com.laphup.controller.utility;

import jakarta.servlet.http.Cookie;

import java.util.Arrays;
import java.util.Optional;

public class CookieUtility {

    public static Optional<Cookie> getCookie(Cookie[] cookies, String name) {
        Optional<Cookie> userCookie = Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals(name))
                .findFirst();
        return userCookie.isPresent()? userCookie: Optional.empty();
    }
}
