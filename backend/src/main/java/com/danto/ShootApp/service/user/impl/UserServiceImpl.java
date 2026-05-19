package com.danto.ShootApp.service.user.impl;

import com.danto.ShootApp.dto.user.CreateUserRequest;
import com.danto.ShootApp.dto.user.CreateUserResponse;
import com.danto.ShootApp.entity.user.UserEntity;
import com.danto.ShootApp.mapper.user.UserMapper;
import com.danto.ShootApp.repository.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public CreateUserResponse createUser(CreateUserRequest request) {
        logger.trace("Creating user {}", request);

        UserEntity newUser = userMapper.toEntity(request);
        newUser.setName(request.name());
        newUser.setSurname(request.surname());
        newUser.setEmail(request.email());
        newUser.setPhoneNumber(request.phoneNumber());

        userRepository.save(newUser);

        return new CreateUserResponse(newUser.getId(), newUser.getName(), newUser.getSurname(), newUser.getEmail(), newUser.getPhoneNumber());
    }

}
