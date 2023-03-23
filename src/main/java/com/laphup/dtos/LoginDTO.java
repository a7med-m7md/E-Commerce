package com.laphup.dtos;

import lombok.Builder;

import java.io.Serializable;

public class LoginDTO implements Serializable{
    private String eMail;
    private String password;

    public LoginDTO(String eMail, String password) {
        this.eMail = eMail;
        this.password = password;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}