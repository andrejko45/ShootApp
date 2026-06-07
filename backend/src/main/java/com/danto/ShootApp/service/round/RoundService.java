package com.danto.ShootApp.service.round;

import com.danto.ShootApp.dto.round.CreateRoundRequest;
import com.danto.ShootApp.dto.round.CreateRoundResponse;

public interface RoundService {

    CreateRoundResponse createRound(CreateRoundRequest request);

}
