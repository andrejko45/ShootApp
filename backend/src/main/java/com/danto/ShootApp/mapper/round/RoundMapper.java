package com.danto.ShootApp.mapper.round;

import com.danto.ShootApp.dto.round.CreateRoundResponse;
import com.danto.ShootApp.entity.round.RoundEntity;

public interface RoundMapper {

      CreateRoundResponse toResponse(RoundEntity round);
}
