package com.laphup.persistence.entities;

public class LoginEntity {

    private String eMail;
    private String password;

    public LoginEntity(String eMail, String password) {
        this.eMail = eMail;
        this.password = password;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String geteMail() {
        return eMail;
    }

    public String getPassword() {
        return password;
    }
}
