package com.danto.ShootApp.mapper.role.impl;

import com.danto.ShootApp.dto.role.CreateRoleRequest;
import com.danto.ShootApp.dto.role.CreateRoleResponse;
import com.danto.ShootApp.entity.role.RoleEntity;
import com.danto.ShootApp.mapper.role.RoleMapper;
import org.springframework.stereotype.Component;



@Component
public class RoleMapperImpl implements RoleMapper {

    @Override
    public RoleEntity toEntity(CreateRoleRequest request) {
        return new RoleEntity(null, request.name(), request.description(), null);
    }

    @Override
    public CreateRoleResponse toResponse(RoleEntity role) {
        return new CreateRoleResponse(role.getId(), role.getName(), role.getDescription());
    }

}
