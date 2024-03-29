package com.laphup.service;

import com.laphup.dtos.UserDto;
import com.laphup.persistence.entities.User;
import com.laphup.persistence.repositoryImp.UserRepo;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.UUID;


public class UserService {
    private UserRepo userRepo;
    public UserService(HttpServletRequest request){
        userRepo = new UserRepo(request);
    }

    public List<User> getAllUsers(){
        return userRepo.getAllUsers();
    }

    public User getUserById(UUID id){
        return userRepo.getUserById(id);
    }

    public User updateUser(UserDto userDto){
        return userRepo.updateUser(userDto);
    }
    public User updateUserEntity(User user){
        return userRepo.updateUserMEntity(user);
    }

}
