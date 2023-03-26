package com.laphup.persistence.entities;

import com.laphup.persistence.entities.CompositeID.OrderDetailsId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class OrderDetails {
    @EmbeddedId
    private OrderDetailsId orderDetailsId;
    @MapsId("orderUuid")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderUuid")
    private Order order;
    @MapsId("uuidLaptop")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uuidLaptop")
    private Laptop laptop;
    private int quantity;


    public OrderDetails() {
    }

    public OrderDetails(OrderDetailsId orderDetailsId, Laptop laptop, int quantity) {
        this.orderDetailsId = orderDetailsId;
        this.laptop = laptop;
        this.quantity = quantity;
    }

    public OrderDetails(Order order, Laptop laptop, int quantity) {
        this.order = order;
        this.laptop = laptop;
        this.quantity = quantity;
    }

    public OrderDetails(OrderDetailsId laptop, int quantity) {
        this.orderDetailsId = laptop;
        this.quantity = quantity;
    }

    public OrderDetailsId getOrderDetailsId() {
        return orderDetailsId;
    }

    public OrderDetails(OrderDetailsId orderDetailsId, Order order, Laptop laptop, int quantity) {
        this.orderDetailsId = orderDetailsId;
        this.order = order;
        this.laptop = laptop;
        this.quantity = quantity;
    }

    public void setOrderDetailsId(OrderDetailsId orderDetailsId) {
        this.orderDetailsId = orderDetailsId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Laptop getLaptop() {
        return laptop;
    }

    public void setLaptop(Laptop laptop) {
        this.laptop = laptop;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
