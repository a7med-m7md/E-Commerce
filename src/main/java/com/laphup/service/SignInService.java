package com.laphup.service;

import com.laphup.dtos.LoginDTO;
import com.laphup.dtos.UserDto;
import com.laphup.persistence.entities.LoginEntity;
import com.laphup.persistence.entities.User;
import com.laphup.persistence.repositoryImp.UserRepo;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;

import java.util.Optional;

public class SignInService {

    HttpServletRequest request;
    UserRepo userDao;
    private ModelMapper modelMapper;


    public SignInService(HttpServletRequest request) {
        this.request = request;
        userDao = new UserRepo(request);
        modelMapper = new ModelMapper();
    }

    public Optional<UserDto> logInto(LoginDTO loginDTO) {
        LoginEntity userEntity = modelMapper.map(loginDTO, LoginEntity.class);
        Optional<User> user = userDao.getByEmail(userEntity.geteMail());
        if (user.isPresent()) {
            UserDto userDto = modelMapper.map(user.get(), UserDto.class);
            return Optional.of(userDto);
        } else {
            return Optional.empty();
        }
    }


}

