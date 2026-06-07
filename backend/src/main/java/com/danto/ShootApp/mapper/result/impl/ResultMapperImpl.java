package com.danto.ShootApp.mapper.result.impl;

import com.danto.ShootApp.dto.result.CreateResultResponse;
import com.danto.ShootApp.entity.result.ResultEntity;
import com.danto.ShootApp.mapper.result.ResultMapper;
import org.springframework.stereotype.Component;

@Component
public class ResultMapperImpl implements ResultMapper {

    @Override
    public CreateResultResponse toResponse(ResultEntity result) {
        return new CreateResultResponse(result.getId(), result.getParticipation().getUser().getName(), result.getRound().getName(), result.getParticipation().getCompetition().getName(), result.getPoints());
    }

}
