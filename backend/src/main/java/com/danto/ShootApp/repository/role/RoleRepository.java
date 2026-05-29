package com.danto.ShootApp.repository.role;

import com.danto.ShootApp.entity.role.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    boolean existsByName(String name);
    Optional<RoleEntity> findByName(String name);
}
