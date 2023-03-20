package com.laphup.dtos;

import com.laphup.persistence.entities.Card;
import com.laphup.persistence.entities.Order;
import com.laphup.persistence.entities.PaymentCard;
import com.laphup.persistence.entities.User;
import com.laphup.util.enums.Gender;
import com.laphup.util.enums.Role;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

public class UserDto implements Serializable {
    private Date birthDay;
    private String fName;
    private String lName;
    private Gender gender;
    private String password;
    private String job;
    private String eMail;
    private long creditLimit;
    private String address;
    private String interests;


    private Role role;

    public UserDto(Date birthDay, String fName, String lName, Gender gender, String password, String job, String eMail, long creditLimit, String address, String interests) {
        this.birthDay = birthDay;
        this.fName = fName;
        this.lName = lName;
        this.gender = gender;
        this.password = password;
        this.job = job;
        this.eMail = eMail;
        this.creditLimit = creditLimit;
        this.address = address;
        this.interests = interests;
//        this.role = role;
    }

    public UserDto() {
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public long getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(long creditLimit) {
        this.creditLimit = creditLimit;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {

        this.role = role;
    }

}
