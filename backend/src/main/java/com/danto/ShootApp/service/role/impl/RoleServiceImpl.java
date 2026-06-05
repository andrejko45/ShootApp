package com.danto.ShootApp.service.role.impl;

import com.danto.ShootApp.dto.DeleteResponse;
import com.danto.ShootApp.dto.role.CreateRoleRequest;
import com.danto.ShootApp.dto.role.CreateRoleResponse;
import com.danto.ShootApp.dto.role.UpdateRoleRequest;
import com.danto.ShootApp.entity.role.RoleEntity;
import com.danto.ShootApp.exceptions.roleExceptions.RoleAlreadyExists;
import com.danto.ShootApp.exceptions.roleExceptions.RoleHasParticipation;
import com.danto.ShootApp.exceptions.roleExceptions.RoleNotFoundException;
import com.danto.ShootApp.mapper.role.RoleMapper;
import com.danto.ShootApp.repository.participation.ParticipationRepository;
import com.danto.ShootApp.repository.role.RoleRepository;
import com.danto.ShootApp.service.role.RoleService;
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
public class RoleServiceImpl implements RoleService {

    private final static Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ParticipationRepository participationRepository;

    @Autowired
    private RoleMapper roleMapper;



    @Override
    public CreateRoleResponse createRole(CreateRoleRequest request) {
        logger.trace("Creating role {}", request);

        if(roleRepository.existsByName(request.name())) {
            throw new RoleAlreadyExists("Role with name: " + request.name() + " already exists !");
        }

        RoleEntity newRole = roleMapper.toEntity(request);
        roleRepository.save(newRole);

        return roleMapper.toResponse(newRole);
    }

    @Override
    public List<CreateRoleResponse> getRoles() {
        logger.trace("Finding all roles !");

        List<CreateRoleResponse> roleList = new ArrayList<>();
        for(RoleEntity r : roleRepository.findAll()) {
            roleList.add(roleMapper.toResponse(r));
        }

        return roleList;
    }

    @Override
    public CreateRoleResponse findRoleByName(String name) {
        logger.trace("Findind a role by name");

        Optional<RoleEntity> roleName = roleRepository.findByName(name);
        if(roleName.isPresent()) {
            return roleMapper.toResponse(roleName.get());
        }
        else {
            throw new RoleNotFoundException("Role with name: " + name + " not found !");
        }
    }

    @Override
    public CreateRoleResponse fullRoleUpdate(UpdateRoleRequest updateRole) {
        logger.trace("Updating role !");

        Optional<RoleEntity> roleUpdated = roleRepository.findById(updateRole.id());
        if(roleUpdated.isPresent()) {

            roleUpdated.get().setName(updateRole.name());
            roleUpdated.get().setDescription(updateRole.description());
            roleRepository.save(roleUpdated.get());
            return roleMapper.toResponse(roleUpdated.get());
        }
        else {
            throw new RoleNotFoundException("Role with ID: " + updateRole.id() + " not found !");
        }
    }


    public DeleteResponse deleteRole(Long id) {

        if(participationRepository.existsByRole_Id(id)) {
            throw new RoleHasParticipation("Role with ID: " + id + " has at least one participation ! Please delete the participation first !");
        }

        boolean exists = roleRepository.existsById(id);
        if(exists) {
            roleRepository.deleteById(id);
            return new DeleteResponse("Role with ID: " + id + " deleted sucessfully !");
        }
        else {
            throw new RoleNotFoundException("Role with ID: " + id + " not found !");
        }


    }



}
