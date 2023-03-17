package com.laphup.service;

import com.laphup.persistence.repositoryImp.UserRepo;
import com.laphup.persistence.entities.User;

import java.util.Optional;

import com.laphup.dtos.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;

public class SingUpService {
    HttpServletRequest request;
    UserRepo userDao;

    public SingUpService(HttpServletRequest request) {
        this.request = request;
        userDao = new UserRepo(request);
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
