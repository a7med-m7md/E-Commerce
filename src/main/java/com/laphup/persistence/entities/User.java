package com.laphup.persistence.entities;

import com.laphup.util.enums.Gender;
import com.laphup.util.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.modelmapper.internal.bytebuddy.implementation.bind.MethodDelegationBinder;

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
    @NonNull
    private Date birthDay;
    @OneToMany(mappedBy = "user")
    private Set<Order> order;
    @OneToMany(mappedBy = "user")
    private Set<Card> card;
    @OneToOne(mappedBy = "user")
    private PaymentCard paymentCard;
    @NonNull
    private String fName;
    @NonNull
    private String lName;
    @NonNull
    private Role role;
    @NonNull
    private Gender gender;
    @NonNull
    private String password;
    private String passwordSult;
    @NonNull
    private String job;
    @NonNull
    @Column(unique = true,nullable = false)
    private String eMail;
    private long creditLimit;
    @NonNull
    private String address;
    @NonNull
    private String interests;
}