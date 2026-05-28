package com.danto.ShootApp.controller.competition;

import com.danto.ShootApp.dto.DeleteResponse;
import com.danto.ShootApp.dto.competition.CreateCompetitionRequest;
import com.danto.ShootApp.dto.competition.CreateCompetitionResponse;
import com.danto.ShootApp.dto.competition.UpdateCompetitionRequest;
import com.danto.ShootApp.service.competition.CompetitionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/competitions")
@RequiredArgsConstructor
public class CompetitionController {

    @Autowired
    private final CompetitionService competitionService;

    @PostMapping
    public CreateCompetitionResponse createCompetition(@Valid @RequestBody CreateCompetitionRequest request) {
        return competitionService.createCompetition(request);
    }

    @GetMapping
    public List<CreateCompetitionResponse> getCompetitions() {
        return competitionService.getCompetitions();
    }

    @GetMapping(path = "/name/{name}")
    public CreateCompetitionResponse findCompByName(@PathVariable String name) {
        return competitionService.findCompByName(name);
    }

    @PutMapping
    public CreateCompetitionResponse updateComp(@Valid @RequestBody UpdateCompetitionRequest request) {
        return competitionService.fullCompUpdate(request);
    }

    @DeleteMapping(path = "/{id}")
    public DeleteResponse deleteCompetition(@PathVariable Long id) {
        return competitionService.deleteCompetition(id);
    }


}
