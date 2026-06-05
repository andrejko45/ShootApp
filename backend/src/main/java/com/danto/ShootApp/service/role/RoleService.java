package com.danto.ShootApp.service.role;

import com.danto.ShootApp.dto.DeleteResponse;
import com.danto.ShootApp.dto.role.CreateRoleRequest;
import com.danto.ShootApp.dto.role.CreateRoleResponse;
import com.danto.ShootApp.dto.role.UpdateRoleRequest;

import java.util.List;

public interface RoleService {

    CreateRoleResponse createRole(CreateRoleRequest request);
    List<CreateRoleResponse> getRoles();
    CreateRoleResponse findRoleByName(String name);
    CreateRoleResponse fullRoleUpdate(UpdateRoleRequest updateRole);
    DeleteResponse deleteRole(Long id);

}
