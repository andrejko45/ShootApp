package com.danto.ShootApp.repository.user;


import com.danto.ShootApp.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {


    UserEntity getUserByEmail(String email);
    boolean existsByEmail(String email);

}
