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

    public Order(User user, Date dateOfPurchasing, long price) {
        this.orderUuid = orderUuid;
        this.orderDetails = orderDetails;
        this.user = user;
        this.dateOfPurchasing = dateOfPurchasing;
        this.price = price;
    }

    public Order(UUID orderUuid) {
        this.orderUuid = orderUuid;
    }

    public Order(UUID orderUuid, Set<OrderDetails> orderDetails, User user, Date dateOfPurchasing, long price) {
        this.orderUuid = orderUuid;
        this.orderDetails = orderDetails;
        this.user = user;
        this.dateOfPurchasing = dateOfPurchasing;
        this.price = price;
    }

    public Order() {
    }

    public UUID getOrderUuid() {
        return orderUuid;
    }

    public void setOrderUuid(UUID orderUuid) {
        this.orderUuid = orderUuid;
    }

    public Set<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Set<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDateOfPurchasing() {
        return dateOfPurchasing;
    }

    public void setDateOfPurchasing(Date dateOfPurchasing) {
        this.dateOfPurchasing = dateOfPurchasing;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
