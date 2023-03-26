package com.laphup.dtos;

import com.laphup.persistence.entities.CompositeID.OrderDetailsId;
import com.laphup.persistence.entities.Laptop;
import com.laphup.persistence.entities.Order;
import jakarta.persistence.*;

public class OrderDetailsDTO {

    private OrderDetailsIdDTO orderDetailsId;
    private OrderDTO order;
    private LaptopDTO laptop;
    private int quantity;

    public OrderDetailsDTO() {

    }

    public OrderDetailsDTO(OrderDTO order, LaptopDTO laptop, int quantity) {
        this.orderDetailsId = orderDetailsId;
        this.order = order;
        this.laptop = laptop;
        this.quantity = quantity;
    }
}
