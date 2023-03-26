package com.laphup.persistence.repositoryImp;

import com.laphup.persistence.entities.Order;
import com.laphup.persistence.repository.BaseRepo;
import jakarta.servlet.http.HttpServletRequest;

import java.util.UUID;

public class OrderRepo extends BaseRepo<Order, UUID> {
    public OrderRepo(HttpServletRequest request) {
        super(request);
    }

    public  void checkOut(Order order) {
        save(order);
    }
}
