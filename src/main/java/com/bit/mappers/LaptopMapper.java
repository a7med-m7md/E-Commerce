package com.bit.mappers;

import com.bit.dtos.LaptopDTO;
import com.bit.persistence.entities.Laptop;
import jakarta.transaction.Synchronization;
import org.modelmapper.ModelMapper;

public class LaptopMapper {

    private ModelMapper modelMapper;

    public LaptopMapper(){
        modelMapper = new ModelMapper();
    }
    public synchronized static LaptopDTO convertToDto(Laptop laptop) {
        ModelMapper modelMapper = new ModelMapper();
        LaptopDTO laptopDTO = modelMapper.map(laptop, LaptopDTO.class);

        return laptopDTO;
    }
    public synchronized Laptop convertToEntity(LaptopDTO laptopDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Laptop laptop = modelMapper.map(laptopDTO, Laptop.class);

        return laptop;
    }
}
