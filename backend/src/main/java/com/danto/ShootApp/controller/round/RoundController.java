package com.danto.ShootApp.controller.round;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danto.ShootApp.dto.round.CreateRoundRequest;
import com.danto.ShootApp.dto.round.CreateRoundResponse;
import com.danto.ShootApp.service.round.RoundService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequestMapping("/rounds")
@RequiredArgsConstructor
public class RoundController {

    private final RoundService roundService;

    @PostMapping
    public CreateRoundResponse createRound(@Valid @RequestBody CreateRoundRequest request) {
      return roundService.createRound(request);
    }


}
