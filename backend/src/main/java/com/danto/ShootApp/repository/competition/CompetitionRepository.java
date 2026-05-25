package com.danto.ShootApp.repository.competition;

import com.danto.ShootApp.dto.competition.CreateCompetitionResponse;
import com.danto.ShootApp.entity.competition.CompetitionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetitionRepository extends JpaRepository<CompetitionEntity, Long> {

    CompetitionEntity getCompetitionByName(String name);
    boolean existsByName(String name);



}
