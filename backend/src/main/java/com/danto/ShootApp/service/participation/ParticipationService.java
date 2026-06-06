package com.danto.ShootApp.service.participation;

import com.danto.ShootApp.dto.DeleteResponse;
import com.danto.ShootApp.dto.participation.CreateParticipationRequest;
import com.danto.ShootApp.dto.participation.CreateParticipationResponse;

import java.util.List;

public interface ParticipationService {

    CreateParticipationResponse createParticipation(CreateParticipationRequest request);
    List<CreateParticipationResponse> getParticipations();
    CreateParticipationResponse findById(Long id);
    DeleteResponse deleteById(Long id);

}
