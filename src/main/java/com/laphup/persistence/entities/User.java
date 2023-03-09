package com.laphup.persistence.entities;

import com.laphup.util.enums.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Setter
@Getter
@Entity
public class User  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID uuid;
    private String name;
    private Gender gender;
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthDay;
    private String password;
    private String passwordSult;
    private String job;
    private String eMail;
    private String creditNo;
    private String creditName;
    private String creditExp;
    private long creditLimit;
    private String address;
    private double interests;
}