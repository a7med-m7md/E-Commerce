package com.laphup.service;

import com.laphup.persistence.repositoryImp.UserRepo;
import com.laphup.persistence.entities.User;

import java.util.Optional;

import com.laphup.dtos.UserDto;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;

public class SingUpService {
    HttpServletRequest request;
    UserRepo userDao;
    private ModelMapper modelMapper;

    public SingUpService(HttpServletRequest request) {
        this.request = request;
        userDao = new UserRepo(request);
        modelMapper  = new ModelMapper();
    }


    public boolean isNewUser(String email) {
        Optional<User> optionalEntity = userDao.getByEmail(email);
        if (optionalEntity.isPresent())
            return true;
        else
            return false;
    }

    public User register(UserDto userDto) {
        System.out.println("in loin");
        System.out.println(userDto.getfName());
        User userEntity = modelMapper.map(userDto, User.class);
        System.out.println(userDto.getfName());
        userDao.save(userEntity);
        return userEntity;
    }

}
