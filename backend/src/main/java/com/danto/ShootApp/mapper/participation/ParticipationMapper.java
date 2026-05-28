package com.danto.ShootApp.mapper.participation;

import com.danto.ShootApp.dto.participation.CreateParticipationResponse;
import com.danto.ShootApp.entity.participation.ParticipationEntity;

public interface ParticipationMapper {

    CreateParticipationResponse toResponse(ParticipationEntity participation);
}
