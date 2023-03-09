package com.bit.service;

import com.bit.dtos.LaptopDTO;
import com.bit.mappers.LaptopMapper;
import com.bit.persistence.daosImp.LaptopDaoImp;
import com.bit.persistence.entities.LaptopCategory;
import com.bit.persistence.entities.Laptop;
import com.bit.util.enums.SortBy;

import java.util.List;
import java.util.stream.Collectors;

public class LaptopService {

    private LaptopDaoImp laptopDao;
    public LaptopService(){
        laptopDao = new LaptopDaoImp();
    }
    public List<LaptopDTO> getPage(int pageNumber, int count, String laptopCategory,
                                   SortBy sortedBy, double minPrice, double maxPrice){
        List<Laptop> laptops = laptopDao.getAll(pageNumber, count, laptopCategory, sortedBy, minPrice, maxPrice);

        return laptops.stream()
                .map(LaptopMapper::convertToDto)
                .collect(Collectors.toList());
    }
}
