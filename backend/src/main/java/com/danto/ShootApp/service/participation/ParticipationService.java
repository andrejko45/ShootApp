package com.danto.ShootApp.service.participation;

import com.danto.ShootApp.dto.participation.CreateParticipationRequest;
import com.danto.ShootApp.dto.participation.CreateParticipationResponse;

public interface ParticipationService {

    CreateParticipationResponse createParticipation(CreateParticipationRequest request);

}
