package com.laphup.mappers;

import com.laphup.dtos.LaptopDTO;
import com.laphup.persistence.entities.Laptop;
import com.laphup.util.ImageConverter;
import org.modelmapper.ModelMapper;

public class LaptopMapper {

    private ModelMapper modelMapper;
    private ImageConverter imageConverter;

    public LaptopMapper() {
        modelMapper = new ModelMapper();
        imageConverter = new ImageConverter();
    }

    public synchronized static LaptopDTO convertToDto(Laptop laptop) {
        ModelMapper modelMapper = new ModelMapper();
        ImageConverter imageConverter = new ImageConverter();
        LaptopDTO laptopDTO = modelMapper.map(laptop, LaptopDTO.class);

        //Set Category as string
        laptopDTO.setLaptopCategory(laptop.getLaptopCategory().getCategoryName());

        //Set rate as string
        laptopDTO.setRate(String.valueOf(laptop.getRate().ordinal()));

        //Set Image list
        laptop.getLaptopImage().forEach(x -> {
            laptopDTO.getImagList().add(x.getImagPath());
        });

        return laptopDTO;
    }

//    public synchronized Laptop convertToEntity(LaptopDTO laptopDTO) {
//        ModelMapper modelMapper = new ModelMapper();
//        ImageConverter imageConverter = new ImageConverter();
//
//        Laptop laptop = modelMapper.map(laptopDTO, Laptop.class);
//
//        try {
//            laptop.setImagPath(imageConverter.converToImage(laptop.getImagPath()));
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//
//
//        return laptop;
//    }
}
