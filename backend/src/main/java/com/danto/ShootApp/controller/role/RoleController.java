package com.danto.ShootApp.controller.role;

import com.danto.ShootApp.dto.DeleteResponse;
import com.danto.ShootApp.dto.role.CreateRoleRequest;
import com.danto.ShootApp.dto.role.CreateRoleResponse;
import com.danto.ShootApp.dto.role.UpdateRoleRequest;
import com.danto.ShootApp.service.role.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping
    public CreateRoleResponse createRole(@Valid @RequestBody CreateRoleRequest request) {
        return roleService.createRole(request);
    }

    @GetMapping
    public List<CreateRoleResponse> getRoles() {
        return roleService.getRoles();
    }

    @GetMapping(path = "/roles/{name}")
    public CreateRoleResponse findRoleByName(@PathVariable String name) {
        return roleService.findRoleByName(name);
    }

    @PutMapping
    public CreateRoleResponse updateRole(@Valid @RequestBody UpdateRoleRequest request) {
        return roleService.fullRoleUpdate(request);
    }

    @DeleteMapping(path = "/{id}")
    public DeleteResponse deleteRole(@PathVariable Long id) {
        return roleService.deleteRole(id);
    }

}
