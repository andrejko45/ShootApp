package com.danto.ShootApp.service.user.impl;

import com.danto.ShootApp.customExceptions.UserNotFoundException;
import com.danto.ShootApp.dto.user.CreateUserRequest;
import com.danto.ShootApp.dto.user.CreateUserResponse;
import com.danto.ShootApp.entity.user.UserEntity;
import com.danto.ShootApp.mapper.user.UserMapper;
import com.danto.ShootApp.repository.user.UserRepository;
import com.danto.ShootApp.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public CreateUserResponse createUser(CreateUserRequest request) {
        logger.trace("Creating user {}", request);


        UserEntity newUser = userMapper.toEntity(request);
        /*
        newUser.setName(request.name());
        newUser.setSurname(request.surname());
        newUser.setEmail(request.email());
        newUser.setPhoneNumber(request.phoneNumber());
        */
        userRepository.save(newUser);

        return userMapper.toResponse(newUser);
    }

    @Override
    public List<CreateUserResponse> getUsers() {
        logger.trace("Finding all users !");

        List<CreateUserResponse> userList = new ArrayList<CreateUserResponse>();
        for(UserEntity r : userRepository.findAll()) {
            userList.add(userMapper.toResponse(r));
        }

        return userList;
    }

    @Override
    public CreateUserResponse findUserByEmail(String email) {
        logger.trace("Finding a user by email!");

        if(userRepository.existsByEmail(email)) {
            CreateUserResponse foundUser = userMapper.toResponse(userRepository.getUserByEmail(email));
            return foundUser;
        }
        else {
            throw new UserNotFoundException("User with email: " + email + " not found.");
        }
    }

}
