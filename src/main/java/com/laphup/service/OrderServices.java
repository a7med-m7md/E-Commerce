package com.laphup.service;

import com.laphup.dtos.LaptopDTO;
import com.laphup.dtos.OrderDTO;
import com.laphup.persistence.entities.Laptop;
import com.laphup.persistence.entities.Order;
import com.laphup.persistence.repositoryImp.LaptopRepoImp;
import com.laphup.persistence.repositoryImp.OrderRepo;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;

public class OrderServices {
    ModelMapper modelMapper = new ModelMapper();
    private LaptopRepoImp laptopRepoImp;
    private OrderRepo orderRepo;

    public OrderServices(HttpServletRequest request) {

        laptopRepoImp = new LaptopRepoImp(request);
        orderRepo = new OrderRepo(request);
    }

    public boolean updateLaptops(LaptopDTO laptopDTO) {
        Laptop laptop = modelMapper.map(laptopDTO, Laptop.class);
        if (laptopRepoImp.updateLaptop(laptop))
            return true;
        return false;
    }

    public void checkOut(OrderDTO orderDTO) {
        Order order = modelMapper.map(orderDTO,Order.class);
        orderRepo.checkOut(order);
    }

}
