package com.laphup.persistence.entities;

import com.laphup.util.enums.Gender;

import java.util.Date;

public class RegisterEntity {

    private String firstName;
    private String lastName;
    private Date birthDate;
    private Gender gender;
    private String job;
    private String address;
    private String eMail;
    private String password;
    private long creditLimit;
    private String interests;

    public RegisterEntity(String firstName, String lastName, Date birthDate, Gender gender, String job, String address, String eMail, String password, long creditLimit, String interests) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.job = job;
        this.address = address;
        this.eMail = eMail;
        this.password = password;
        this.creditLimit = creditLimit;
        this.interests = interests;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCreditLimit(long creditLimit) {
        this.creditLimit = creditLimit;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    public String getJob() {
        return job;
    }

    public String getAddress() {
        return address;
    }

    public String geteMail() {
        return eMail;
    }

    public String getPassword() {
        return password;
    }

    public long getCreditLimit() {
        return creditLimit;
    }

    public String getInterests() {
        return interests;
    }
}
