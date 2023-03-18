package com.laphup.service;

import com.laphup.dtos.LoginDTO;
import com.laphup.dtos.UserDto;
import com.laphup.persistence.daos.UserDao;
import com.laphup.persistence.entities.LoginEntity;
import com.laphup.persistence.entities.User;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;

import java.util.Optional;

public class SignInService {

    HttpServletRequest request;
    UserDao userDao;
    private ModelMapper modelMapper;


    public SignInService(HttpServletRequest request) {
        this.request = request;
        userDao = new UserDao(request);
        modelMapper = new ModelMapper();
    }

    public Optional<User> logInto(LoginDTO loginDTO) {
        LoginEntity userEntity = modelMapper.map(loginDTO, LoginEntity.class);
        Optional<User> user = userDao.getByEmail(userEntity.geteMail());
        if (user.isPresent())
            return user;
        return Optional.empty();
    }

}

