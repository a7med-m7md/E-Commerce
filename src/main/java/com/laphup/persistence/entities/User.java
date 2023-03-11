package com.laphup.persistence.entities;

import com.laphup.util.enums.Gender;
import com.laphup.util.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@Entity
public class User  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthDay;
    @OneToMany(mappedBy = "user")
    private Set<Order> order;
    @OneToMany(mappedBy = "user")
    private Set<Card> card;
    private String fName;
    private String lName;
    private Role role;
    private Gender gender;
    private String password;
    private String passwordSult;
    private String job;
    private String eMail;
    private String creditNo;
    private String creditName;
    private String creditExp;
    private long creditLimit;
    private String address;
    private String interests;
}