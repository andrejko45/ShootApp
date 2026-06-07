package com.danto.ShootApp.controller.result;

import com.danto.ShootApp.dto.DeleteResponse;
import com.danto.ShootApp.dto.result.CreateResultRequest;
import com.danto.ShootApp.dto.result.CreateResultResponse;
import com.danto.ShootApp.service.result.ResultService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/results")
@RequiredArgsConstructor
public class ResultController {

    private final ResultService resultService;

    @PostMapping
    public CreateResultResponse createResult(@Valid @RequestBody CreateResultRequest request) {
        return resultService.createResult(request);
    }

    @GetMapping
    public List<CreateResultResponse> getResults() {
        return resultService.getResults();
    }

    @GetMapping(path = "/search/{id}")
    public CreateResultResponse findById(@PathVariable Long id) {
        return resultService.getById(id);
    }

    @DeleteMapping(path = "/{id}")
    public DeleteResponse deleteById(@PathVariable Long id) {
        return resultService.deleteById(id);
    }


}
