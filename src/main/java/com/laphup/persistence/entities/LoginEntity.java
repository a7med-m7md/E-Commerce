package com.laphup.persistence.entities;

import java.io.Serializable;

public class LoginEntity implements Serializable {

    private String eMail;
    private String password;

    public LoginEntity(String eMail, String password) {
        this.eMail = eMail;
        this.password = password;
    }

    public LoginEntity() {
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