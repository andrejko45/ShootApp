package com.danto.ShootApp.repository.participation;

import com.danto.ShootApp.entity.participation.ParticipationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipationRepository extends JpaRepository<ParticipationEntity, Long> {


    boolean existsByUser_Id(Long id);
    boolean existsByCompetition_Id(Long id);

}
