package com.danto.ShootApp.mapper.competition;

import com.danto.ShootApp.dto.competition.CreateCompetitionRequest;
import com.danto.ShootApp.dto.competition.CreateCompetitionResponse;
import com.danto.ShootApp.entity.competition.CompetitionEntity;

public interface CompetitionMapper {

    CompetitionEntity toEntity(CreateCompetitionRequest request);
    CreateCompetitionResponse toResponse(CompetitionEntity competition);

}
