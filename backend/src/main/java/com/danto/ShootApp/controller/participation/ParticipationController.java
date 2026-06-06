package com.danto.ShootApp.controller.participation;

import com.danto.ShootApp.dto.DeleteResponse;
import com.danto.ShootApp.dto.participation.CreateParticipationRequest;
import com.danto.ShootApp.dto.participation.CreateParticipationResponse;
import com.danto.ShootApp.service.participation.ParticipationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/participations")
@RequiredArgsConstructor
public class ParticipationController {

    private final ParticipationService participationService;

    @PostMapping
    public CreateParticipationResponse createParticipation(@RequestBody CreateParticipationRequest request) {
        return participationService.createParticipation(request);
    }

    @GetMapping
    public List<CreateParticipationResponse> getParticipations() {
        return participationService.getParticipations();
    }

    @GetMapping(path = "/id/{id}")
    public CreateParticipationResponse getPartById(@PathVariable Long id) {
        return participationService.findById(id);
    }

    @DeleteMapping(path = "/{id}")
    public DeleteResponse deletePartById(@PathVariable Long id) {
        return participationService.deleteById(id);
    }

}
