package com.laphup.service;

import com.laphup.persistence.entities.LaptopImage;
import com.laphup.persistence.repository.BaseRepo;
import jakarta.servlet.http.HttpServletRequest;

import java.util.UUID;

public class ImageService extends BaseRepo<LaptopImage, UUID> {
    public ImageService(HttpServletRequest request) {
        super(request);
    }

    public void saveImage(LaptopImage laptopImage){
        merge(laptopImage);
    }
}
