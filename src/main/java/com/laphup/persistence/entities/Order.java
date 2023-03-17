package com.laphup.persistence.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@Entity(name = "PurchasedOrder")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID orderUuid;
    @OneToMany(mappedBy = "order")
    private Set<OrderDetails> orderDetails;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userUuid")
    private User user;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfPurchasing;
    private long price;
}
