package com.laphup.service;

import com.laphup.dtos.LaptopDTO;
import com.laphup.mappers.LaptopMapper;
import com.laphup.persistence.daosImp.LaptopDaoImp;
import com.laphup.persistence.entities.Laptop;
import com.laphup.util.enums.SortBy;

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
