package com.danto.ShootApp.mapper.role;

import com.danto.ShootApp.dto.role.CreateRoleRequest;
import com.danto.ShootApp.dto.role.CreateRoleResponse;
import com.danto.ShootApp.entity.role.RoleEntity;

public interface RoleMapper {

    RoleEntity toEntity(CreateRoleRequest request);
    CreateRoleResponse toResponse(RoleEntity role);

}
