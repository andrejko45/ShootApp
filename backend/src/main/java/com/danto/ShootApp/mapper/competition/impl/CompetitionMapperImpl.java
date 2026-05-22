package com.danto.ShootApp.mapper.competition.impl;

import com.danto.ShootApp.dto.competition.CreateCompetitionRequest;
import com.danto.ShootApp.dto.competition.CreateCompetitionResponse;
import com.danto.ShootApp.entity.competition.CompetitionEntity;
import com.danto.ShootApp.mapper.competition.CompetitionMapper;
import org.springframework.stereotype.Component;

@Component
public class CompetitionMapperImpl implements CompetitionMapper {

    @Override
    public CompetitionEntity toEntity(CreateCompetitionRequest request) {
        return new CompetitionEntity(null, request.name(), request.place(), request.date(), null, null);
    }

    @Override
    public CreateCompetitionResponse toResponse(CompetitionEntity competition) {
        return new CreateCompetitionResponse(competition.getId(), competition.getName(), competition.getPlace(), competition.getDate());
    }
}
