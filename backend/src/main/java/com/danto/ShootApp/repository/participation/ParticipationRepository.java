package com.danto.ShootApp.repository.participation;

import com.danto.ShootApp.entity.participation.ParticipationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipationRepository extends JpaRepository<ParticipationEntity, Long> {


    boolean existsByUser_Id(Long id);
    boolean existsByCompetition_Id(Long id);
    boolean existsByRole_Id(Long id);
    boolean existsByUser_IdAndCompetition_Id(Long idU, Long idC); // Overenie ci dany pouzivatel nema uz ucast v danej sutazi

}
