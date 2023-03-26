package com.laphup.service;

import com.laphup.dtos.LaptopDTO;
import com.laphup.dtos.OrderDTO;
import com.laphup.persistence.entities.Laptop;
import com.laphup.persistence.entities.Order;
import com.laphup.persistence.entities.OrderDetails;
import com.laphup.persistence.repository.OrderdetailsRepo;
import com.laphup.persistence.repositoryImp.LaptopRepoImp;
import com.laphup.persistence.repositoryImp.OrderRepo;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;

public class OrderServices {
    ModelMapper modelMapper = new ModelMapper();
    private LaptopRepoImp laptopRepoImp;
    private OrderRepo orderRepo;

    private OrderdetailsRepo orderdetailsRepo;

    public OrderServices(HttpServletRequest request) {

        laptopRepoImp = new LaptopRepoImp(request);
        orderRepo = new OrderRepo(request);
        orderdetailsRepo = new OrderdetailsRepo(request);
    }

    public boolean updateLaptops(Laptop laptopEntity) {
        if (laptopRepoImp.updateLaptop(laptopEntity))
            return true;
        return false;
    }

    public void checkOut(Order orderEntity) {
        orderRepo.checkOut(orderEntity);
    }

    public void saveOrderDetails(OrderDetails orderDetails) {
        orderdetailsRepo.saveOrderDetails(orderDetails);
    }

}
