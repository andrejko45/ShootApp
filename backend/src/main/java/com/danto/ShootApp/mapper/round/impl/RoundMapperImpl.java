package com.danto.ShootApp.mapper.round.impl;

import org.springframework.stereotype.Component;


import com.danto.ShootApp.dto.round.CreateRoundResponse;
import com.danto.ShootApp.entity.round.RoundEntity;

@Component
public class RoundMapperImpl {

  public CreateRoundResponse toResponse(RoundEntity round) {
    return new CreateRoundResponse(round.getId(), round.getName(), round.getCompetition().getName(), round.getOrderIndex(), round.getDescription());
  }


}
