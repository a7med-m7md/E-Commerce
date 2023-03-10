package com.laphup.persistence.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID uuid;
    @OneToMany(mappedBy = "order")
    private Set<OrderDetails> orderDetails;
    @ManyToOne
    @JoinColumn(name = "uuid")
    private User user;
    private Date dateOfPurchasing;
    private long price;
}
