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
}
