package com.danto.ShootApp.controller.participation;

import com.danto.ShootApp.dto.participation.CreateParticipationRequest;
import com.danto.ShootApp.dto.participation.CreateParticipationResponse;
import com.danto.ShootApp.service.participation.ParticipationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/participations")
@RequiredArgsConstructor
public class ParticipationControlller {

    private final ParticipationService participationService;

    @PostMapping
    public CreateParticipationResponse createParticipation(@RequestBody CreateParticipationRequest request) {
        return participationService.createParticipation(request);
    }


}
