package com.danto.ShootApp.mapper.participation.impl;

import com.danto.ShootApp.dto.participation.CreateParticipationResponse;
import com.danto.ShootApp.entity.participation.ParticipationEntity;
import com.danto.ShootApp.mapper.participation.ParticipationMapper;
import org.springframework.stereotype.Component;

@Component
public class ParticipationMapperImpl implements ParticipationMapper {

    @Override
    public CreateParticipationResponse toResponse(ParticipationEntity participation) {
        return new CreateParticipationResponse(participation.getId(), participation.getUser().getName(), participation.getCompetition().getName(), participation.getRole().getName());
    }

}
