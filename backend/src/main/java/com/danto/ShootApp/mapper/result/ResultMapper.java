package com.danto.ShootApp.mapper.result;

import com.danto.ShootApp.dto.result.CreateResultResponse;
import com.danto.ShootApp.entity.result.ResultEntity;

public interface ResultMapper {

    CreateResultResponse toResponse(ResultEntity result);

}
