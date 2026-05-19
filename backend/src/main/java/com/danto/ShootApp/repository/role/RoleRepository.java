package com.danto.ShootApp.repository.role;

import com.danto.ShootApp.entity.role.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
}
