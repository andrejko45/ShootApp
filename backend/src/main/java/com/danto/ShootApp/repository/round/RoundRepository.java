package com.danto.ShootApp.repository.round;

import com.danto.ShootApp.entity.round.RoundEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoundRepository extends JpaRepository<RoundEntity, Long> {

    boolean existsByCompetition_IdAndName(Long idC, String name);
    boolean existsByCompetition_IdAndOrderIndex(Long idC, Integer orderIndex);
    Optional<RoundEntity> findByCompetition_IdAndName(Long idC, String name);

}
