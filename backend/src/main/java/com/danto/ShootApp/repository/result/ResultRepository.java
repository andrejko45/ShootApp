package com.danto.ShootApp.repository.result;

import com.danto.ShootApp.entity.result.ResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepository extends JpaRepository<ResultEntity, Long> {

    boolean existsByParticipation_IdAndRound_Id(Long partId, Long roundId);
    boolean existsByRound_Id(Long id);
    boolean existsByParticipation_Id(Long id);
}
