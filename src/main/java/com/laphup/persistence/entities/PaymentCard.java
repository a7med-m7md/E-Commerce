package com.laphup.persistence.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Getter
@Setter
public class PaymentCard {
    @OneToOne
    @PrimaryKeyJoinColumn
    private User user;
    @Id
    /*@GenericGenerator(name="UserIdGenerator", strategy="foreign",
            parameters=@Parameter(name="property", value="user"))
    @GeneratedValue(generator="UserIdGenerator")*/
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID paymentCardUuid;
    @NonNull
    private String creditNo;
    @NonNull
    private String creditName;
    @NonNull
    private String creditExp;
}
