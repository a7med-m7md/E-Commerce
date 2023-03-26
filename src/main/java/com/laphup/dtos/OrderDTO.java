package com.laphup.dtos;

import com.laphup.persistence.entities.OrderDetails;
import com.laphup.persistence.entities.User;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

public class OrderDTO {
    private UUID orderUuid;
    private Set<OrderDetailsDTO> orderDetails;
    private UserDto user;
    private Date dateOfPurchasing;
    private long price;

    public OrderDTO() {

    }

    public OrderDTO(UserDto user, Date dateOfPurchasing, long price) {
        this.user = user;
        this.dateOfPurchasing = dateOfPurchasing;
        this.price = price;
    }

    public UUID getOrderUuid() {
        return orderUuid;
    }

    public void setOrderUuid(UUID orderUuid) {
        this.orderUuid = orderUuid;
    }

    public Set<OrderDetailsDTO> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Set<OrderDetailsDTO> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
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
