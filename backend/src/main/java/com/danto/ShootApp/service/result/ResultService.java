package com.danto.ShootApp.service.result;

import com.danto.ShootApp.dto.DeleteResponse;
import com.danto.ShootApp.dto.result.CreateResultRequest;
import com.danto.ShootApp.dto.result.CreateResultResponse;

import java.util.List;

public interface ResultService {

    CreateResultResponse createResult(CreateResultRequest request);
    List<CreateResultResponse> getResults();
    CreateResultResponse getById(Long id);
    DeleteResponse deleteById(Long id);
}
