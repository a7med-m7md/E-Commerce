package com.laphup.dtos;

import com.laphup.persistence.entities.Card;
import com.laphup.persistence.entities.Order;
import com.laphup.util.enums.Gender;
import com.laphup.util.enums.Role;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

public class UserDto implements Serializable {

    private UUID uuid;
    private Date birthDay;
    private Set<Order> order;
    private Set<Card> card;
    private String fName;
    private String lName;
    private Role role;
    private Gender gender;
    private String job;
    private String eMail;
    private String creditNo;
    private String creditName;
    private String creditExp;
    private long creditLimit;
    private String address;
    private String interests;

    public UserDto(UUID uuid, Date birthDay, Set<Order> order, Set<Card> card, String fName, String lName, Role role, Gender gender, String job, String eMail, String creditNo, String creditName, String creditExp, long creditLimit, String address, String interests) {
        this.uuid = uuid;
        this.birthDay = birthDay;
        this.order = order;
        this.card = card;
        this.fName = fName;
        this.lName = lName;
        this.role = role;
        this.gender = gender;
        this.job = job;
        this.eMail = eMail;
        this.creditNo = creditNo;
        this.creditName = creditName;
        this.creditExp = creditExp;
        this.creditLimit = creditLimit;
        this.address = address;
        this.interests = interests;
    }

    public UserDto(Date birthDay, String fName, String lName, Gender gender, String job, String eMail, String address, String interests ,Long creditLimit) {
        this.birthDay = birthDay;
        this.fName = fName;
        this.lName = lName;
        this.gender = gender;
        this.job = job;
        this.eMail = eMail;
        this.creditLimit = creditLimit;
        this.address = address;
        this.interests = interests;
        this.creditLimit = creditLimit;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public void setOrder(Set<Order> order) {
        this.order = order;
    }

    public void setCard(Set<Card> card) {
        this.card = card;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public void setCreditNo(String creditNo) {
        this.creditNo = creditNo;
    }

    public void setCreditName(String creditName) {
        this.creditName = creditName;
    }

    public void setCreditExp(String creditExp) {
        this.creditExp = creditExp;
    }

    public void setCreditLimit(long creditLimit) {
        this.creditLimit = creditLimit;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public Set<Order> getOrder() {
        return order;
    }

    public Set<Card> getCard() {
        return card;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public Role getRole() {
        return role;
    }

    public Gender getGender() {
        return gender;
    }

    public String getJob() {
        return job;
    }

    public String geteMail() {
        return eMail;
    }

    public String getCreditNo() {
        return creditNo;
    }

    public String getCreditName() {
        return creditName;
    }

    public String getCreditExp() {
        return creditExp;
    }

    public long getCreditLimit() {
        return creditLimit;
    }

    public String getAddress() {
        return address;
    }

    public String getInterests() {
        return interests;
    }
}
