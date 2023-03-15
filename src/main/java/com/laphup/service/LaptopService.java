package com.laphup.service;

import com.laphup.dtos.LaptopDTO;
//import com.laphup.mappers.LaptopMapper;
//import com.laphup.persistence.daosImp.LaptopDaoImp;
import com.laphup.persistence.entities.Laptop;
import com.laphup.persistence.entities.LaptopCategory;
import com.laphup.persistence.entities.LaptopImage;
import com.laphup.persistence.repositoryImp.LaptopRepoImp;
import jakarta.servlet.http.HttpServletRequest;

public class LaptopService {

    private LaptopRepoImp laptopRepoImp;
    public LaptopService(HttpServletRequest httpServletRequest){
        laptopRepoImp = new LaptopRepoImp(httpServletRequest);
    }

    public void addLaptop(Laptop laptop){
        laptopRepoImp.save(laptop);
    }

    public void saveImage(LaptopImage laptopImage) {
        laptopRepoImp.save(laptopImage);
    }

//    public List<LaptopDTO> getPage(int pageNumber, int count, String laptopCategory,
//                                   SortBy sortedBy, double minPrice, double maxPrice){
//        List<Laptop> laptops = laptopDao.getAll(pageNumber, count, laptopCategory, sortedBy, minPrice, maxPrice);
//
//        return laptops.stream()
//                .map(LaptopMapper::convertToDto)
//                .collect(Collectors.toList());
//    }
}

