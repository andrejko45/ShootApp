package com.danto.ShootApp.service.competition;

import com.danto.ShootApp.dto.DeleteResponse;
import com.danto.ShootApp.dto.competition.CreateCompetitionRequest;
import com.danto.ShootApp.dto.competition.CreateCompetitionResponse;
import com.danto.ShootApp.dto.competition.UpdateCompetitionRequest;

import java.util.List;

public interface CompetitionService {

    CreateCompetitionResponse createCompetition(CreateCompetitionRequest request);
    List<CreateCompetitionResponse> getCompetitions();
    CreateCompetitionResponse fullCompUpdate(UpdateCompetitionRequest request);
    CreateCompetitionResponse findCompByName(String name);
    DeleteResponse deleteCompetition(Long id);

}
