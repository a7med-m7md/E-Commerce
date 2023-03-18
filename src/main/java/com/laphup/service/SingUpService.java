package com.laphup.service;

import com.laphup.mappers.UserMapper;
import com.laphup.persistence.daos.UserDao;
import com.laphup.persistence.entities.User;

import java.util.Optional;

import com.laphup.dtos.UserDto;
import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;

public class SingUpService {
    HttpServletRequest request;
    UserDao userDao;

    public SingUpService(HttpServletRequest request) {
        this.request = request;
        userDao = new UserDao(request);
    }

    private ModelMapper modelMapper = new ModelMapper();
    public boolean isNewUser(String email) {
        Optional<User> optionalEntity = userDao.getByEmail(email);
        if (optionalEntity.isPresent())
            return true;
        else
            return false;
    }

    public User register(UserDto userDto) {
        User userEntity = modelMapper.map(userDto, User.class);
        userDao.save(userEntity);
        return userEntity;
    }

}
