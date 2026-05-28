package com.danto.ShootApp.repository.competition;

import com.danto.ShootApp.entity.competition.CompetitionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface CompetitionRepository extends JpaRepository<CompetitionEntity, Long> {

    CompetitionEntity getCompetitionByName(String name);
    boolean existsByName(String name);
    List<CompetitionEntity> findByPlace(String place);
    List<CompetitionEntity> findByDate(LocalDate date);


}
