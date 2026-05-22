package com.danto.ShootApp.service.user.impl;

import com.danto.ShootApp.dto.user.DeleteResponse;
import com.danto.ShootApp.dto.user.UpdateUserRequest;
import com.danto.ShootApp.exceptions.userExceptions.UserHasParticipationException;
import com.danto.ShootApp.exceptions.userExceptions.UserNotFoundException;
import com.danto.ShootApp.dto.user.CreateUserRequest;
import com.danto.ShootApp.dto.user.CreateUserResponse;
import com.danto.ShootApp.entity.user.UserEntity;
import com.danto.ShootApp.mapper.user.UserMapper;
import com.danto.ShootApp.repository.participation.ParticipationRepository;
import com.danto.ShootApp.repository.user.UserRepository;
import com.danto.ShootApp.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Validated
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ParticipationRepository participationRepository;

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
            throw new UserNotFoundException("User with email: " + email + " not found !");
        }
    }

    @Override
    public CreateUserResponse findUserById(Long id) {
        logger.trace("Finding a user by ID!");

        Optional<UserEntity> userById = userRepository.findById(id);
        if(userById.isPresent()) {
            return userMapper.toResponse(userById.get());
        }
        else {
            throw new UserNotFoundException("User with ID: " + id + " not found !");
        }
    }

    @Override
    public CreateUserResponse fullUserUpdate(UpdateUserRequest updateUser) {
        logger.trace("Updating a user!");
        Optional<UserEntity> userToBeU = userRepository.findById(updateUser.id());
        if(userToBeU.isPresent()) {
            userToBeU.get().setName(updateUser.name());
            userToBeU.get().setSurname(updateUser.surname());
            userToBeU.get().setEmail(updateUser.email());
            userToBeU.get().setPhoneNumber(updateUser.phoneNumber());
            userRepository.save(userToBeU.get());
            return userMapper.toResponse(userToBeU.get());
        }
        else {
            throw new UserNotFoundException("User " + updateUser.name() + " " + updateUser.surname() + " not found !");
        }

    }

    @Override
    public DeleteResponse deleteUser(Long id) {
        logger.trace("Deleting a user with ID: " + id);

        if(participationRepository.existsByUser_Id(id)) {
            throw new UserHasParticipationException("User with ID: " + id + " has particiption in at least one competition ! Please delete the participation first !");
        }

        boolean exists = userRepository.existsById(id);
        if(exists) {
            userRepository.deleteById(id);
            return new DeleteResponse("User with ID: " + id + " deleted sucessfully !");
        }
        else {
            throw new UserNotFoundException("User with ID: " + id + " not found");
        }

    }

}
