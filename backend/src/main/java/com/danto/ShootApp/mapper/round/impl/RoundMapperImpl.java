package com.danto.ShootApp.mapper.round.impl;

import org.springframework.stereotype.Component;


import com.danto.ShootApp.dto.round.CreateRoundResponse;
import com.danto.ShootApp.entity.round.RoundEntity;
import com.danto.ShootApp.mapper.round.RoundMapper;

@Component
public class RoundMapperImpl implements RoundMapper{

  public CreateRoundResponse toResponse(RoundEntity round) {
    return new CreateRoundResponse(round.getId(), round.getName(), round.getCompetition().getName(), round.getOrderIndex(), round.getDescription());
  }


}
