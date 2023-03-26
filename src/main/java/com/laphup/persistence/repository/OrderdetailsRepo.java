package com.laphup.persistence.repository;

import com.laphup.persistence.entities.OrderDetails;
import jakarta.servlet.http.HttpServletRequest;

import java.util.UUID;

public class OrderdetailsRepo extends BaseRepo<OrderDetails, UUID> {

    public OrderdetailsRepo(HttpServletRequest request) {
        super(request);
    }

    public void saveOrderDetails(OrderDetails orderDetails) {
        save(orderDetails);
    }

}
