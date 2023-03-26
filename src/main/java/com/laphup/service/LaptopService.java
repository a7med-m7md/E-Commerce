package com.laphup.service;

import com.laphup.dtos.LaptopDTO;
import com.laphup.mappers.LaptopMapper;
import com.laphup.persistence.entities.LaptopImage;
import com.laphup.persistence.repositoryImp.LaptopRepoImp;
import com.laphup.persistence.entities.Laptop;
import com.laphup.util.enums.SortBy;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class LaptopService {

    private LaptopRepoImp laptopRepoImp;
    public LaptopService(HttpServletRequest request){
        laptopRepoImp = new LaptopRepoImp(request);
    }
    public List<LaptopDTO> getPage(int pageNumber, int count, String[] laptopCategory,
                                   SortBy sortedBy, double minPrice, double maxPrice){
        List<Laptop> laptops = laptopRepoImp.getPage(pageNumber, count, laptopCategory, sortedBy, minPrice, maxPrice);

        return laptops.stream()
                .map(LaptopMapper::convertToDto)
                .collect(Collectors.toList());
    }

    public void addLaptop(Laptop laptop){
        laptopRepoImp.save(laptop);
    }

    public void saveImage(LaptopImage laptopImage) {
        laptopRepoImp.saveImages(laptopImage);
    }


    public  void updateLaptop(Laptop laptop){
        laptopRepoImp.save(laptop);
    }


    public LaptopDTO getLaptop (UUID uuid){
        Laptop laptop = laptopRepoImp.getById(uuid);

        LaptopDTO laptopDTO = LaptopMapper.convertToDto(laptop);

        return laptopDTO;
    }

    public Laptop getNLaptop(UUID uuid){
        Laptop laptop = laptopRepoImp.getById(uuid);
        return laptop;
    }

    public Long getLaptopCount(){
        return laptopRepoImp.getLaptopCount();
    }

    public boolean removeLaptop(Laptop laptop) {
        return laptopRepoImp.removeLaptop(laptop);
    }
}
