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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uuid")
    private Order order;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uuid")
    private Laptop laptop;
    private int quantity;
}
