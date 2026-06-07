package com.danto.ShootApp.service.round;

import com.danto.ShootApp.dto.DeleteResponse;
import com.danto.ShootApp.dto.round.CreateRoundRequest;
import com.danto.ShootApp.dto.round.CreateRoundResponse;

import java.util.List;

public interface RoundService {

    CreateRoundResponse createRound(CreateRoundRequest request);
    CreateRoundResponse getByCompIdAndName(Long compId, String name);
    List<CreateRoundResponse> getAllRounds();
    DeleteResponse deleteById(Long id);

}
